package com.vanhieu.entity;

import javax.persistence.*;

@Entity
@Table(name = "blog")
public class BlogEntity extends BaseEntity{

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "shortdescription", nullable = false,  columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "content", nullable = false,  columnDefinition = "TEXT")
    private String content;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_blog", nullable = false)
    private CategoryEntity blogCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_blog", nullable = false)
    private UserEntity authorBlog;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CategoryEntity getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(CategoryEntity blogCategory) {
        this.blogCategory = blogCategory;
    }

    public UserEntity getAuthorBlog() {
        return authorBlog;
    }

    public void setAuthorBlog(UserEntity authorBlog) {
        this.authorBlog = authorBlog;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BlogEntity{" +
                "title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}
