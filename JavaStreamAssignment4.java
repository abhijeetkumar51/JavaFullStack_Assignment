import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    String dept;
    int salary;
    List<String> skills;

    Employee(int id, String name, String dept, int salary, List<String> skills) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public int getSalary() {
        return salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "("+ name + " : " + dept + " :" + salary+")";
    }
}

public class JavaStreamAssignment4 {

    public static void main(String[] args) {

        List <Employee> employee = List.of(
            new Employee(1, "Anant", "IT", 70000, List.of("Java", "Spring")),
            new Employee(2, "Rani", "HR", 40000, List.of("Recruitment")),
            new Employee(3, "Nehal", "IT", 90000, List.of("Java", "Docker")),
            new Employee(4, "Pooja", "Finance", 60000, List.of("Excel", "Accounts")),
            new Employee(4, "Pooja", "Electronics", 60000, List.of("Transformer", "Electrics"))
        );

        // Employee e = new Employee(
        //         1,
        //         "Aman",
        //         "IT",
        //         70000,
        //         List.of("Java", "Spring")
        // );

        // System.out.println(e);
        // System.out.println("Skills: " + e.getSkills());
        //employee.forEach(System.out::println);
        List<Employee> a = employee.stream().filter(e -> e.getSalary()>60000).toList();
        System.out.println("Employees with Salary greater than 60000 is : "+a);
        System.out.println();

        List<String> b = employee.stream().map(Employee::getName).toList();
        System.out.println("Employees name are: "+b);
        System.out.println();

        List<String> c = employee.stream().map(Employee::getName).distinct().toList();
        System.out.println("Distinct Employees name: "+c);
        System.out.println();

        List<Employee> d = employee.stream().sorted((m,n) -> n.getSalary()- m.getSalary()).toList();
        System.out.println("Salary in Descinding order: "+d);
        System.out.println();

        List<Employee> e = employee.stream().sorted((m,n) -> n.getSalary() - m.getSalary()).skip(1).limit(2).toList();
        System.out.println("2nd anf 3rd Highest pais Salary: "+e);
        System.out.println();

        Set<String> f = employee.stream().flatMap(emp ->emp.getSkills().stream()).collect(Collectors.toSet());
        System.out.println("All unique Skills: "+f);
        System.out.println();

        int g =employee.stream().map(Employee::getSalary)  .reduce(0, Integer::sum);
        System.out.println("Reduce - Total Salary: "+g);
        System.out.println();

        double h=employee.stream().map(Employee::getSalary).reduce(0, Integer::sum) /(double) employee.stream().count();
        System.out.println("Average Salary:(map,reduce,count) "+h);
        System.out.println();

        Map<String, List<Employee>> i =employee.stream().collect(Collectors.groupingBy(Employee::getDept));
        System.out.println("Grouping employess by Department: "+i);
        System.out.println();

        Map<String, Employee> j =employee.stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.collectingAndThen(
                                        Collectors.maxBy(
                                                Comparator.comparingInt(Employee::getSalary)
                                        ),
                                        Optional::get
                                )
                        ));
                        System.out.println("Highest paid employee per department"+j);
                        System.out.println();



        // List<String> k =employee.stream().filter(e -> e.getDept().equals("IT"))
        //                 .filter(e -> e.getSalary() > 60000)
        //                 .flatMap(e -> e.getSkills().stream())
        //                 .distinct()
        //                 .sorted()
        //                 .limit(3)
        //                 .toList();






        Map<String, Map<String, Double>> l =employee.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDept,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> {
                                            double total =
                                                    list.stream()
                                                        .map(Employee::getSalary)
                                                        .reduce(0, Integer::sum);

                                            double count = list.size();
                                            double average = total / count;

                                            Map<String, Double> report = new HashMap<>();
                                            report.put("total", total);
                                            report.put("average", average);
                                            report.put("count", count);

                                            return report;
                                        }
                                )
                        ));
                        System.out.println("Department Salary Report: "+l);




    }
}
