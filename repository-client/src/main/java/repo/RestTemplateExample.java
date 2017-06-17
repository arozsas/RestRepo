package repo;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.client.RestClientException;
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
            try {
                Repository repository = new Repository(repoClientConfig.getId(), repoClientConfig.getOwner());
                RepositoryResponse response = restTemplate.postForObject(URI, repository, RepositoryResponse.class);
                System.out.println(response.toString());
            } catch (RestClientException | HttpMessageConversionException e) {
                System.out.println(e.getMessage());
            }
        }

        if (repoClientConfig.getAction().equals("get")) {
            String getUrl = UriComponentsBuilder.fromUriString(URI).queryParam("id", repoClientConfig.getId()).build().toUriString();
            try {
                RepositoryResponse response = restTemplate.getForObject(getUrl, RepositoryResponse.class, Collections.singletonMap("id", repoClientConfig.getId()));
                System.out.println(response.toString());
            } catch (RestClientException | HttpMessageConversionException e) {
                System.out.println(e.getMessage());
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
