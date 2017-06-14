package repo;

import org.kohsuke.args4j.Option;

public class RepoClientConfig {

    @Option(name = "--action")
    public String action;

    @Option(name = "--id")
    public String id;

    @Option(name = "--owner")
    public String owner;

    @Option(name = "--count")
    public int count;

    public String getAction() {
        return action;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public int getCount() {
        return count;
    }
}
