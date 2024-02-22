import java.util.InputMismatchException;
import java.util.Scanner;

public class Shopping {


    public static void main(String[] args) {
        int defaultArrayLength = 8;
        int productCount = 0;
        String[] shoppingList = new String[defaultArrayLength];

        Scanner scanner = new Scanner(System.in);

        System.out.println("Вас приветствует список покупок!");

        while (true) {
            System.out.println("----------------------------");
            System.out.println("Выберите одну из команд:");
            System.out.println("1. Добавить товар в список");
            System.out.println("2. Показать список");
            System.out.println("3. Очистить список");
            System.out.println("4. Завершить работу");
            System.out.print("----------------------------\nВведите команду: ");

            // Защита на случай если пользователь введет нечто отличное от цифр.
            try {
                int actionNumber = scanner.nextInt();

                switch (actionNumber) {
                    case 1: // Добавление нового товара.
                        System.out.print("Напишите название товара без пробелов.\nНапример 'Пакет_молока': ");
                        String productName = scanner.next();

                        // Проверка на повторы товаров.
                        boolean isProductRepeated = false;

                        for (int i = 0; i < productCount; i++) {
                            /* assert shoppingList[i] != null; - я не хотел обрабатывать эту ошибку, ибо думал,
                            *  что null не может появиться, но idea предлагала добавить строчку,
                            *  а поскольку на прошедшем вебинаре по среде разработки нам сказали, что idea в большенстве
                            *  случаев советует полезные вещи, к которым стоит прислушиваться, то решил не спорить.
                            *  P.s. Большое спасибо за ревью.
                            */
                            isProductRepeated = shoppingList[i].equals(productName);

                            if (isProductRepeated) {
                                break;
                            }
                        }

                        // Если повторов нет
                        if (!isProductRepeated) {
                            // Проверяем на заполненность массив товаров. Если заполнен то увеличиваем.
                            if (productCount == shoppingList.length) {
                                int newLengthArray = productCount + productCount / 2;

                                String[] extendedShoppingList = new String[newLengthArray];
                                System.arraycopy(shoppingList, 0, extendedShoppingList, 0, productCount);

                                shoppingList = extendedShoppingList;
                            }

                            shoppingList[productCount] = productName;
                            productCount++;

                            System.out.println(shoppingList[productCount - 1] + " внесен в список товаров.\n");

                        } else {
                            System.out.println("Такой товар уже есть в списке\n");

                        }

                        break;

                    case 2: // Показать список товаров
                        // Если список не пустой
                        if (productCount != 0) {
                            System.out.println("Ваш список товаров:");
                            for (int i = 0; i < productCount; i++) {
                                System.out.println((i + 1) + ". " + shoppingList[i]);
                            }

                        } else {
                            System.out.println("Список товаров пуст!");

                        }

                        System.out.print("\n");
                        break;

                    case 3: // Очищаем спиок товаров
                        if (productCount != 0){
                            shoppingList = new String[defaultArrayLength];
                            productCount = 0;
                        }

                        System.out.println("Список товаров очищен!\n");
                        break;

                    case 4: // Завершение программы
                        System.out.println("Завершение работы.");
                        return;

                    default:
                        System.out.println("Неизвестная команда!\n");
                }
            } catch (InputMismatchException e) {
                System.out.println(e + "\nПрограмма ожидает цифровую команду\n");
                /*  При использовании scanner.nextInt(), без низлежащей строчки программа ведет себя неадекватно:
                *   начинается бесконечный цикл считывания строки с сообщением об ошибке в переменную  actionNumber,
                *   если ввести строку, а не число.
                *   Я не смог придумать лудшего способа исправить такое поведение.
                *   Именно поэтому в первой версии и считывал строку, из которой уже парсил число.
                */
                scanner.nextLine();
            }
        }
    }
}