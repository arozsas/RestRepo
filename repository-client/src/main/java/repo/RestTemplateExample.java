package repo;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

public class RestTemplateExample {

    private static RepoClientConfig repoClientConfig = new RepoClientConfig();

    public static void main(String[] args) {

        parseCmdLine(args);

        RepoClient repoClient = new RepoClient();

        if (repoClientConfig.getAction().equals("create")) {
            repoClient.createRepository(repoClientConfig.getId(), repoClientConfig.getOwner());
        }

        if (repoClientConfig.getAction().equals("get")) {
            repoClient.getRepository(repoClientConfig.getId());
        }

        if (repoClientConfig.getAction().equals("delete")) {
            repoClient.deleteRepository(repoClientConfig.getId());
        }

        if (repoClientConfig.getAction().equals("filter")) {
            repoClient.getRepositoriesByCounter(repoClientConfig.getCount());
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
