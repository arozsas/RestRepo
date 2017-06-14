package repo;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

public class RestTemplateExample {

    private static final String URI = "http://localhost:8080/repo";
    private static RepoClientConfig repoClientConfig = new RepoClientConfig();

    public static void main(String[] args) {

        parseCmdLine(args);

        RestTemplate restTemplate = new RestTemplate();

        if (repoClientConfig.getAction().equals("create")) {
            Repository repository = new Repository(repoClientConfig.getId(), repoClientConfig.getOwner());
            try {
                ResponseEntity<Repository> response = restTemplate.postForEntity(URI, repository, Repository.class);
                System.out.println(response.getBody().toString());
            } catch (HttpClientErrorException e) {
                System.out.println(e.getStatusCode() + " " + e.getStatusText());
            }
        }

        if (repoClientConfig.getAction().equals("get")) {
            String getUrl = UriComponentsBuilder.fromUriString(URI).queryParam("id", repoClientConfig.getId()).build().toUriString();
            try {
                ResponseEntity<Repository> response = restTemplate.getForEntity(getUrl, Repository.class, Collections.singletonMap("id", repoClientConfig.getId()));
                System.out.println(response.getBody().toString());
            } catch (HttpClientErrorException e) {
                System.out.println(e.getStatusCode() + " " + e.getStatusText());
            }
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
