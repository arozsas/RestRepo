package repo;

import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RepoClient {

    private static final String URI = "http://localhost:8080/repo";
    private static final String GET_BY_COUNTER_URI = "http://localhost:8080/repo/getReposByCounter";
    private RestTemplate restTemplate = new RestTemplate();

    public void createRepository(final String id, final String owner) {
        try {
            Repository repository = new Repository(id, owner);
            RepositoryResponse response = restTemplate.postForObject(URI, repository, RepositoryResponse.class);
            System.out.println(response.toString());
        } catch (RestClientException | HttpMessageConversionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getRepository(final String id) {
        String getUrl = UriComponentsBuilder.fromUriString(URI).queryParam("id", id).build().toUriString();
        try {
            RepositoryResponse response = restTemplate.getForObject(getUrl, RepositoryResponse.class);
            System.out.println(response.toString());
        } catch (RestClientException | HttpMessageConversionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRepository(final String id) {
        String url = UriComponentsBuilder.fromUriString(URI).queryParam("id", id).build().toUriString();
        try {
            restTemplate.delete(url, Collections.singletonMap("id", id));
            System.out.println("Repository with id " + id + " successfully deleted!");
        } catch (RestClientException | HttpMessageConversionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getRepositoriesByCounter(final int count) {
        String url = UriComponentsBuilder.fromUriString(GET_BY_COUNTER_URI).queryParam("count", count).build().toUriString();
        try {
            Repository[] repositoriesArr = restTemplate.getForObject(url, Repository[].class);
            List<Repository> repositories = Arrays.asList(repositoriesArr);
            repositories.forEach(repository -> System.out.println(repository));
        } catch (RestClientException | HttpMessageConversionException e) {
            System.out.println(e.getMessage());
        }
    }
}
