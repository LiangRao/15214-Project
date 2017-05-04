package edu.cmu.cs.cs214.hw6.sequential.sentiment;

import edu.cmu.cs.cs214.hw6.taskservice.util.Task;
import org.eclipse.egit.github.core.CommitUser;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class GithubAnalysis implements Task, Serializable{
    private String taskName;
    /**
     * data structures to store messages and sentiment results
     */
    private final Map<Author, List<Message>> messages_author = new HashMap<>();
    private final Map<String, List<Message>> messages_lang = new HashMap<>();
    private final Map<String, List<Message>> messages_org = new HashMap<>();
    private final Map<Author, SentimentResult> sentiments_author = new HashMap<>();
    private final Map<String, SentimentResult> sentiments_lang = new HashMap<>();
    private final Map<String, SentimentResult> sentiments_org = new HashMap<>();

    private static final int MAX_COMMIT_PAGES = 2;

    /**
     * Return the first part of repositories
     * @return the first part of repositories
     */
    private List<Repository> getRepositoriesFirst() {
        List<Repository> repositories = new ArrayList<>();
        repositories.add(new Repository("google", "material-design-icons"));
        repositories.add(new Repository("google", "guava"));
        repositories.add(new Repository("google", "web-starter-kit"));

//        repositories.add(new Repository("facebook", "hhvm"));
//        repositories.add(new Repository("facebook", "pop"));
//        repositories.add(new Repository("travis-ci", "travis-ci"));
//        repositories.add(new Repository("travis-ci", "travis.rb"));
//        repositories.add(new Repository("travis-ci", "travis-cookbooks"));
//        repositories.add(new Repository("travis-ci", "dpl"));
//        repositories.add(new Repository("travis-ci", "travis-web"));
//        repositories.add(new Repository("Microsoft", "vscode"));
//        repositories.add(new Repository("Microsoft", "TypeScript"));
//        repositories.add(new Repository("Microsoft", "CNTK"));
//        repositories.add(new Repository("Microsoft", "dotnet"));
//        repositories.add(new Repository("Microsoft", "ChakraCore"));
        return repositories;
    }

    /**
     * Return the second part of repositories
     * @return the second part of repositories
     */
    private List<Repository> getRepositoriesSecond() {
        List<Repository> repositories = new ArrayList<>();
        repositories.add(new Repository("mozilla", "pdf.js"));
        repositories.add(new Repository("mozilla", "BrowserQuest"));
        repositories.add(new Repository("mozilla", "metrics-graphics"));
        return repositories;
    }
    /**
     * Return the third part of repositories
     * @return the third part of repositories
     */
    private List<Repository> getRepositoriesThird() {
        List<Repository> repositories = new ArrayList<>();
        repositories.add(new Repository("facebook", "react"));
        repositories.add(new Repository("facebook", "react-native"));
        repositories.add(new Repository("facebook", "immutable-js"));
        return repositories;
    }

    /**
     * Download the first repositories's data and page
     * @return the log
     * @throws IOException
     */
    public String downloadFirst() throws IOException {
        StringBuilder sb = new StringBuilder();
        GitHubClient github = new GitHubClient();
        //set up with oauth token for higher rate limit
//        github.setOAuth2Token("<yourtoken>");

        //repositories to analyze
        List<Repository> repositories = getRepositoriesFirst();

        for (Repository repo : repositories) {
            System.out.printf("Downloading data for %s%n", repo.getIdentifier());
            sb.append("Downloading data for" + repo.getIdentifier() +"\n");
            //looking up most popular language in repository
            String language = getTopLanguage(github, repo);

            //looking up commits (paged), up to 5 pages
            CommitService commitService = new CommitService(github);
            PageIterator<RepositoryCommit> commitPages = commitService.pageCommits(repo);
            int pageCounter = 1;
            for (Collection<RepositoryCommit> commitsOnPage : commitPages) {
                System.out.printf(" - downloading page %d%n", pageCounter);
                sb.append(" - downloading page " + pageCounter +"\n");
                for (RepositoryCommit commit : commitsOnPage) {
                    //skip merge commits (with more than 1 parents)
                    if (commit.getParents().size() > 1)
                        continue;

                    //for each nonmerge commit, collect messages_author by author, language, and organization
                    CommitUser gitAuthor = commit.getCommit().getAuthor();
                    Author author = new Author(gitAuthor.getName(), gitAuthor.getEmail());
                    Message message = new Message(commit.getCommit().getMessage());
                    synchronized (messages_author) {
                        addToData(messages_author, author, message);
                    }
                    synchronized (messages_lang) {
                        addToData(messages_lang, language, message);
                    }
                    synchronized (messages_org) {
                        addToData(messages_org, repo.getOrg(), message);
                    }
                }

                if (++pageCounter > MAX_COMMIT_PAGES) break;
            }
        }
        return sb.toString();
    }
    /**
     * Download the second repositories's data and page
     * @return the log
     * @throws IOException
     */
    public String downloadSecond() throws IOException {
        StringBuilder sb = new StringBuilder();
        GitHubClient github = new GitHubClient();
        //set up with oauth token for higher rate limit
//        github.setOAuth2Token("<yourtoken>");

        //repositories to analyze
        List<Repository> repositories = getRepositoriesSecond();

        for (Repository repo : repositories) {
            System.out.printf("Downloading data for %s%n", repo.getIdentifier());
            sb.append("Downloading data for" + repo.getIdentifier() +"\n");
            //looking up most popular language in repository
            String language = getTopLanguage(github, repo);

            //looking up commits (paged), up to 5 pages
            CommitService commitService = new CommitService(github);
            PageIterator<RepositoryCommit> commitPages = commitService.pageCommits(repo);
            int pageCounter = 1;
            for (Collection<RepositoryCommit> commitsOnPage : commitPages) {
                System.out.printf(" - downloading page %d%n", pageCounter);
                sb.append(" - downloading page " + pageCounter +"\n");
                for (RepositoryCommit commit : commitsOnPage) {
                    //skip merge commits (with more than 1 parents)
                    if (commit.getParents().size() > 1)
                        continue;

                    //for each nonmerge commit, collect messages_author by author, language, and organization
                    CommitUser gitAuthor = commit.getCommit().getAuthor();
                    Author author = new Author(gitAuthor.getName(), gitAuthor.getEmail());
                    Message message = new Message(commit.getCommit().getMessage());

                    synchronized (messages_author) {
                        addToData(messages_author, author, message);
                    }
                    synchronized (messages_lang) {
                        addToData(messages_lang, language, message);
                    }
                    synchronized (messages_org) {
                        addToData(messages_org, repo.getOrg(), message);
                    }
                }

                if (++pageCounter > MAX_COMMIT_PAGES) break;
            }
        }
        return sb.toString();
    }
    /**
     * Download the Third repositories's data and page
     * @return the log
     * @throws IOException
     */
    public String downloadThird() throws IOException {
        StringBuilder sb = new StringBuilder();
        GitHubClient github = new GitHubClient();
        //set up with oauth token for higher rate limit
//        github.setOAuth2Token("<yourtoken>");

        //repositories to analyze
        List<Repository> repositories = getRepositoriesThird();

        for (Repository repo : repositories) {
            System.out.printf("Downloading data for %s%n", repo.getIdentifier());
            sb.append("Downloading data for" + repo.getIdentifier() +"\n");
            //looking up most popular language in repository
            String language = getTopLanguage(github, repo);

            //looking up commits (paged), up to 5 pages
            CommitService commitService = new CommitService(github);
            PageIterator<RepositoryCommit> commitPages = commitService.pageCommits(repo);
            int pageCounter = 1;
            for (Collection<RepositoryCommit> commitsOnPage : commitPages) {
                System.out.printf(" - downloading page %d%n", pageCounter);
                sb.append(" - downloading page " + pageCounter +"\n");
                for (RepositoryCommit commit : commitsOnPage) {
                    //skip merge commits (with more than 1 parents)
                    if (commit.getParents().size() > 1)
                        continue;

                    //for each nonmerge commit, collect messages_author by author, language, and organization
                    CommitUser gitAuthor = commit.getCommit().getAuthor();
                    Author author = new Author(gitAuthor.getName(), gitAuthor.getEmail());
                    Message message = new Message(commit.getCommit().getMessage());

                    synchronized (messages_author) {
                        addToData(messages_author, author, message);
                    }
                    synchronized (messages_lang) {
                        addToData(messages_lang, language, message);
                    }
                    synchronized (messages_org) {
                        addToData(messages_org, repo.getOrg(), message);
                    }
                }

                if (++pageCounter > MAX_COMMIT_PAGES) break;
            }
        }
        return sb.toString();
    }

    /**
     * Compile author's sentiments
     * @return the log
     * @throws IOException
     */
    public String compileFirst() throws IOException {
        System.out.println("Running sentiment analysis");
        computeSentiments(messages_author, sentiments_author);
        return "Running sentiment analysis /n";
    }

    /**
     * Compile lang's sentiments
     * @return the log
     * @throws IOException
     */
    public String compileSecond() throws IOException {
        computeSentiments(messages_lang, sentiments_lang);
        return "";
    }

    /**
     * Compile org's sentiments
     * @return the log
     * @throws IOException
     */
    public String compileThird() throws IOException {
        computeSentiments(messages_org, sentiments_org);
        return "";
    }

    /**
     * Print the result of the analysis
     * @return log of result
     */
    public String printResult(){
        StringBuilder sb = new StringBuilder();
        System.out.println("Results by author");
        sb.append("Results by author\n");
        String author = printSentimentsSorted(sentiments_author);
        sb.append(author);

        System.out.println("Results by language");
        String lang = printSentimentsSorted(sentiments_lang);
        sb.append("Results by language");
        sb.append(lang);

        System.out.println("Results by organization");
        String org = printSentimentsSorted(sentiments_org);
        sb.append("Results by organization\n");
        sb.append(org);
        return sb.toString();
    }



    /**
     * will print all the entries in the map, sorted from the most negative sentiment to
     * the most positive
     */
    private <K> String printSentimentsSorted(Map<K, SentimentResult> sentiments) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Map.Entry<K, SentimentResult>> results = new ArrayList<>(sentiments.entrySet());
        Collections.sort(results, (a, b) -> a.getValue().average() - b.getValue().average() > 0 ? 1 : -1);
        for (Map.Entry<K, SentimentResult> result : results) {
            System.out.printf(" - %s: %s%n", result.getKey().toString(), result.getValue().summary());
            sb.append(" - ");
            sb.append(result.getKey().toString());
            sb.append(": ");
            sb.append(result.getValue().summary());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * run the sentiment analysis on a list of messages (grouped by some K) and store
     * the aggregated results by K in the result map.
     *
     * @param messages input messages for which sentiments should be computed
     * @param result   map holding the result
     */
    private <K> void computeSentiments(Map<K, List<Message>> messages, Map<K, SentimentResult> result) throws IOException {
        for (Map.Entry<K, List<Message>> messageList : messages.entrySet())
            if (messageList.getValue().size() >= 10) {
                //computing sentiments for all messages, grouping them by author
                System.out.printf("  - computing sentiments for %d messages%n", messageList.getValue().size());
                SentimentResult sentiment = new SentimentResult();
                for (Message msg : messageList.getValue()) {
                    sentiment.mergeWith(msg.getSentiment());
                }

                result.put(messageList.getKey(), sentiment);
            }
    }

    /**
     * run query from Github to get the most popular programming language from the
     * repository (as detected by Github)
     */
    private String getTopLanguage(GitHubClient github, Repository repo) throws IOException {
        RepositoryService repoService = new RepositoryService(github);
        Map<String, Long> languages = repoService.getLanguages(repo);
        String language = "";
        long maxLoc = 0;
        for (Map.Entry<String, Long> lang : languages.entrySet())
            if (lang.getValue() > maxLoc) {
                language = lang.getKey();
                maxLoc = lang.getValue();
            }
        return language;
    }


    /**
     * helper method to add data to a list inside a map
     *
     * @param map  map to be changed
     * @param key  key to identify entry where to add data
     * @param data data to be added
     * @param <K>  type of the keys
     * @param <D>  type of the entries in the value's list
     */
    private <K, D> void addToData(Map<K, List<D>> map, K key, D data) {
        List<D> values = map.getOrDefault(key, new ArrayList<>());
        values.add(data);
        map.put(key, values);
    }

    @Override
    public List<Set<String>> getFunctionName() {
        List<Set<String>> resultList = new ArrayList<>();
        Set<String> download = new HashSet<>();
        download.add("downloadFirst");
        download.add("downloadSecond");
        download.add("downloadThird");
        Set<String> compile = new HashSet<>();
        compile.add("compileFirst");
        compile.add("compileSecond");
        compile.add("compileThird");
        Set <String> result = new HashSet<>();
        result.add("printResult");
        resultList.add(download);
        resultList.add(compile);
        resultList.add(result);
        return resultList;
    }

    @Override
    public File getWorkingDirectory() {
        return null;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void setTaskName(String name) {
        taskName =name;
    }
}
