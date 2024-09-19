import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Найдём количество несовершеннолетних:
        long minor = persons.stream()  //создаём поток
                .filter(person -> person.getAge() < 18) //фильтруем людей младше 18
                .count(); //ссуммируем отфильтрованные значения

        //список фамилий призывников
        List<String> recruits = persons.stream() // создаём поток
                .filter(person -> person.getSex() == Sex.MAN) //отбираем мужчин
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                //.filter(person -> person.getAge()<27) //фильтруем по призывному возрасту
                .map(Person::getFamily) //получаем фамилии
                .toList();

        //список потенциально работоспособных людей с высшим образованием
        List<Person> ableToWork = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER) //фильтруем по образованию
                .filter(person -> (person.getSex() == Sex.MAN&&person.getAge() >= 18 && person.getAge() < 65)
                ||(person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() < 60))  //фильтр по полу и возрасту
                .sorted(Comparator.comparing(Person::getFamily))  // сортируем по фамилии
                .toList();  //добавляем в список





//        for (Person p:ableToWork){
//            System.out.println(p.toString());
//        }

    }
}