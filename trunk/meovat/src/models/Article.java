/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Khoa
 */
public class Article {
    private String Id;
    private String Title;
    private String Content;
    private String TitlePath;
    
    public Article(String Title, String Content, String Id) {
        this.Title = Title;
        this.Content = Content;
        this.Id = Id;
    }
    
    public Article(String Title, String Id) {
        this.Title = Title;
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
    
    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return the Content
     */
    public String getContent() {
        return Content;
    }

    /**
     * @param Content the Content to set
     */
    public void setContent(String Content) {
        this.Content = Content;
    }

    /**
     * @return the TitlePath
     */
    public String getTitlePath() {
        return TitlePath;
    }

    /**
     * @param TitlePath the TitlePath to set
     */
    public void setTitlePath(String TitlePath) {
        this.TitlePath = TitlePath;
    }
}
