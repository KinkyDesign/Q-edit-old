package qedit.clients.ontol;

import java.util.Collection;

public interface DCMetaInfo {

    String getComment();

    String getDescription();

    String getIdentifier();

    String getSameAs();

    String getSeeAlso();

    String getTitle();

    String getVersionInfo();

    String getPublisher();

    String getCreator();

    String getHasSource();

    Collection<String> getContributors();

    Collection<String> getAudiences();

    void setComment(String comment);

    void setDescription(String description);

    void setIdentifier(String identifier);

    void setSameAs(String sameAs);

    void setSeeAlso(String seeAlso);

    void setTitle(String title);

    void setVersionInfo(String versionInfo);

    void setPublisher(String publisher);

    void setCreator(String creator);

    void setHasSource(String hasSource);

    void addContributor(String contributor);

    void addAudience(String audience);


}
