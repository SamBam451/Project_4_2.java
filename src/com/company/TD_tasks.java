package com.company;


class TD_item implements Comparable<TD_item>{
    private String title;
    private String description;
    private String priority;

    public TD_item(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "TD_item{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

    public String display(){
        String text;
        text = this.getTitle()+ " | Description: " + this.getDescription() + " | Priority: " + this.getPriority();
        return text;
    }

    @Override
    public int compareTo(TD_item o) {
        int result = this.priority.compareTo(o.priority);
        if(result == 0){
            return this.title.compareTo((o.title));
        }else{
            return result;
        }
    }
}
