package repo;

import java.util.Collections;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Agnes_Rozsas
 */
public class RestTemplateExample {

    private static final String URI_POST = "http://localhost:8080/repos";
    private static final String URI_GET = "http://localhost:8080/repo";
    private static RepoClientConfig repoClientConfig = new RepoClientConfig();

    public static void main(String[] args) {

        parseCmdLine(args);

        RestTemplate restTemplate = new RestTemplate();

        if (repoClientConfig.getAction().equals("create")) {
            Repository repository = new Repository(repoClientConfig.getId(), repoClientConfig.getOwner());
            Repository repositoryResp = restTemplate.postForObject(URI_POST, repository, Repository.class);
            System.out.println(repositoryResp.toString());
        }
        if (repoClientConfig.getAction().equals("get")) {
            String getUrl = UriComponentsBuilder.fromUriString(URI_GET).queryParam("id", repoClientConfig.getId()).build().toUriString();
            Repository response = restTemplate.getForObject(getUrl, Repository.class, Collections.singletonMap("id", repoClientConfig.getId()));
            System.out.println(response.toString());
        }
    }

    private static void parseCmdLine(String[] args) {
        CmdLineParser cmdLineParser = new CmdLineParser(repoClientConfig);
        try {
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
    }
}
