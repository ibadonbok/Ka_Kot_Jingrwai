package hymn.book.kakotjingrwai01;

public class search {

    private String id;
    private String name;


    public search(String id, String name, int image) {
        this.id = id;
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
