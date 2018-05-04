package br.com.wedosoft.global.resource;

import java.util.List;

public final class Page<T> {

    private List<T> content;

    private Integer firstResult;

    private Integer maxResults;

    private String link;

    private Integer numberOfElements;

    private Integer numberOfListElements;

    Page() {
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public List<T> getContent() {
        return content;
    }

    void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public String getLink() {
        return link;
    }

    void setLink(String link) {
        this.link = link;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Integer getNumberOfListElements() {
        return numberOfListElements;
    }

    public void setNumberOfListElements(Integer numberOfListElements) {
        this.numberOfListElements = numberOfListElements;
    }

    public static class Builder<T> {

        private Page<T> page = new Page<>();

        public Builder<T> content(List<T> content) {
            page.setContent(content);
            return this;
        }

        public Builder<T> firstResult(Integer firstResult) {
            page.setFirstResult(firstResult);
            return this;
        }

        public Builder<T> maxResults(Integer maxResults) {
            page.setMaxResults(maxResults);
            return this;
        }

        public Builder<T> numberOfElements(Integer numberOfElements) {
            page.setNumberOfElements(numberOfElements);
            return this;
        }

        public Builder<T> linkAnterior(String linkAnterior) {
            page.setLink(linkAnterior);
            return this;
        }

        public Builder<T> numberOfListElements(Integer numberOfListElements) {
            page.setNumberOfListElements(numberOfListElements);
            return this;
        }

        public Page<T> build() {
            return page;
        }

    }

}
