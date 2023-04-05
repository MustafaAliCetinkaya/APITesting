package APITests.week6;

public class SpartanPojo {
    private int id;
    private Long phone;
    private String name;
    private String gender;

    public SpartanPojo() {

    }

    public SpartanPojo(int id, Long phone, String name, String gender) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "SpartanPojo{" +
                "id=" + id +
                ", phone=" + phone +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
