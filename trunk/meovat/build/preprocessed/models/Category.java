/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Khoa
 */
public class Category {
    private String CategoryName;
    private String CategoryPath;
    private String ImagePath;
            
    public Category(String CategoryName, String CategoryPath, String ImagePath) {
        this.CategoryName = CategoryName;
        this.CategoryPath = CategoryPath;
        this.ImagePath = ImagePath;
    }
    
    public String getCategoryName() {
        return CategoryName;
    }

    public String getCategoryPath() {
        return CategoryPath;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public void setCategoryPath(String CategoryPath) {
        this.CategoryPath = CategoryPath;
    }

    /**
     * @return the ImagePath
     */
    public String getImagePath() {
        return ImagePath;
    }

    /**
     * @param ImagePath the ImagePath to set
     */
    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }
    
}
