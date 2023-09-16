public class Person {
    private int id;
    private String name;
    private int age;
    private String adress;
    private double salary;

    public Person(int id, String name, int age, String adress, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.adress = adress;
        this.salary = salary;
    }

    public Person() {
    }
    private Person (Builder builder) {

        id = builder.id;
        name = builder.name;
        age = builder.age;
        adress = builder.adress;
        salary = builder.salary;



    }

    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public String getAdress() {
        return adress;
    }


    public double getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {

        private int id;
        private String name;
        private int age;
        private String adress;
        private double salary;


        public Builder() {


        }

        public Builder id (int val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder adress (String val){
            adress =val;
            return  this;
        }
        public  Builder salary (int val){
            salary= val;
            return this;
        }
        public Person build() {
            return new Person(this);
        }
    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", adress='" + adress + '\'' +
                ", salary=" + salary +
                '}';
    }
}
