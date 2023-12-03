package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.view.SortingProcessView;

public abstract class SortingProcessState<T extends Comparable<T>> {
    private final SortingProcessView<T> sortingProcessView;

    public SortingProcessState(SortingProcessView<T> sortingProcessView) {
        this.sortingProcessView = sortingProcessView;
    }

    public abstract SortingProcessState<T> next();

    public SortingProcessView<T> getSortingProcessView() {
        return sortingProcessView;
    }
}
//    void sort(int A[], int n) {
//        int i, i1, i2, s, k;
//        for (s = 1; 1; s *= 2) { // Размер группы кратен степени 2
//            int nn = n / s; // Количество групп по s элементов
//            if (n % s != 0) nn++; // Остаток – есть неполная группа
//            int n1 = nn / 2 * s; // Деление ближе к середине,
//            int n2 = n - n1; // но кратно размеру группы
//            if (n1 <= 0 || n2 <= 0) return; // Часть больше целого - выход
//            int * B1 = new int[n1], * B2 = new int[n2];
//            for (i = 0; i < n1; i++) B1[i] = A[i]; // Разделение на части
//            for (i = 0; i < n2; i++) B2[i] = A[i + n1];
//            i1 = i2 = 0;
//            for (i = 0, k = 0; i < n; i++) { // Слияние с переходом «скачком»
//                if (i1 == s && i2 == s) // при достижении границ обеих
//                    k += s, i1 = 0, i2 = 0; // групп
//                if (i1 == s || k + i1 == n1) A[i] = B2[k + i2++]; // Достигла границы группы или
//                else // массива
//                    if (i2 == s || k + i2 == n2) A[i] = B1[k + i1++];
//                    else // Если нет – минимальный из пары
//                        if (B1[k + i1] < B2[k + i2]) A[i] = B1[k + i1++];
//                        else A[i] = B2[k + i2++];
//            }
//            delete[] B1;
//            delete[] B2;
//        }
//    }