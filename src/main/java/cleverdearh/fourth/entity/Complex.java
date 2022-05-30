package cleverdearh.fourth.entity;

public class Complex {// множественный класс
    double real; // реальная часть
    double image; // мнимая часть

    public Complex(double real, double image) {// конструктор с параметрами
        this.real = real;
        this.image = image;
    }

    public Complex() {
        // TODO Auto-generated constructor stub
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImage() {
        return image;
    }

    public void setImage(double image) {
        this.image = image;
    }

    public Complex add(Complex complex) {// Добавить комплексные числа
        double real2 = complex.getReal();
        double image2 = complex.getImage();
        double newReal = real + real2;
        double newImage = image + image2;
        Complex result = new Complex(newReal, newImage);
        return result;
    }

    public Complex sub(Complex complex) {// Вычитаем комплексные числа
        double real2 = complex.getReal();
        double image2 = complex.getImage();
        double newReal = real - real2;
        double newImage = image - image2;
        Complex result = new Complex(newReal, newImage);
        return result;
    }

    public Complex mul(Complex a) {// умножить комплексные числа
        double real2 = a.getReal();
        double image2 = a.getImage();
        double newReal = real * real2 - image * image2;
        double newImage = image * real2 + real * image2;
        Complex result = new Complex(newReal, newImage);
        return result;
    }

    public Complex div(Complex complex) {// Делим комплексные числа
        double real2 = complex.getReal();
        double image2 = complex.getImage();
        double newReal = (real * real2 + image * image2) / (real2 * real2 + image2 * image2);
        double newImage = (image * real2 - real * image2) / (real2 * real2 + image2 * image2);
        Complex result = new Complex(newReal, newImage);
        return result;
    }

    public void print() {// используется для форматирования вывода
        if (image > 0) {
            System.out.println(real + " + " + image + "i");
        } else if (image < 0) {
            System.out.println(real + "" + image + "i");
        } else {
            System.out.println(real);
        }
    }

    public double module() {
        return Math.sqrt(real * real + image * image);
    }

    // Инкапсулируем конкретные операции, в основном для преобразования ввода и вывода
    public static void talk(String data1_1, String data1_2, String data2_1, String data2_2, String operation) {

        // Следующее утверждение - преобразование формата
        double dat1_1 = Double.parseDouble(data1_1); // преобразовать вещественную часть строки в double
        double dat1_2 = Double.parseDouble(data1_2.substring(0, data1_2.length() - 1)); // преобразовать мнимую часть строкового типа с удаленным «i» в двойной тип
        double dat2_1 = Double.parseDouble(data2_1); // преобразовать вещественную часть строки в double
        double dat2_2 = Double.parseDouble(data2_2.substring(0, data2_2.length() - 1)); // Преобразовать мнимую часть строки с удаленным «i» в double

        Complex num1 = new Complex(dat1_1, dat1_2);
        Complex num2 = new Complex(dat2_1, dat2_2);

        Complex result;
        int a, b;

        if (operation.equals("+")) {// добавить два числа
            result = num1.add(num2);
            result.print();
        }

        if (operation.equals("-")) {// Вычесть два числа
            result = num1.sub(num2);
            result.print();
        }

        if (operation.equals("*")) {// умножить два числа
            result = num1.mul(num2);
            result.print();
        }

        if (operation.equals("/")) {// Разделить два числа

            if (num2.real * num2.real + num2.image * num2.image != 0) {
                result = num1.div(num2);
                result.print();
            } else {
                System.out.println("Произошла ошибка операции, делитель не может быть 0в операции делителя, введите его снова!");
            }
        }
    }
}