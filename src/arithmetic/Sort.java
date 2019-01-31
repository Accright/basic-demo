package arithmetic;

import java.util.Arrays;

public class Sort {

    //这个是用来记录堆排序的一个数据 从而用来调整排序后未排序的新的堆
    static int len;

    public static void main(String[] args){
        int[] l = {1,32,43,12,312,3,3,54,32,322,31,65,87,435,123,8,76,76567,5};
        //new Sort().bubbleSort(l);
        //new Sort().bubbleSoryAdvance(l);
        //new Sort().quickSort(l,0,l.length - 1);
        //new Sort().insertSort(l);
        //new Sort().binaryInsertSort(l);
        //new Sort().chooseSort(l);
        //new Sort().shellSort(l);
        //new Sort().heapSort(l);
        new Sort().mergeSort(l);

        System.out.println("排序之后的结果是"+Arrays.toString(l));
    }

    //冒泡排序
    public void bubbleSort(int[] x){
        for (int i = 0;i < x.length; i++){
            for (int j = 0;j < x.length - i - 1;j++){
                if (x[j] > x[j+1]){
                    swap(j,j+1,x);
                }
            }
        }
    }

    //改进的冒泡排序
    public void bubbleSoryAdvance(int x[]){
        int low = 0;
        int high = x.length - 1;
        while (low < high){
            //从两端分别开始冒泡
            for (int i = low;i < high;i++){
                if (x[i] > x[i + 1]){
                    swap(i,i+1,x);
                }
            }
            --high;//排完一次序需要移动low和high
            for (int j = high;j > low;j--){
                if (x[j] < x[j - 1]){
                    swap(j,j-1,x);
                }
            }
            ++low;
        }
    }

    //快排的recursive
    public void quickSort(int x[],int low,int high){
        if (low < high){
            int middle = this.getKey(x,low,high);
            this.quickSort(x,0,middle - 1);
            this.quickSort(x,middle + 1,high);
        }
    }

    //快排 交换并返回目前的基准值
    public int getKey(int[] x,int low,int high){
        int temp = x[low];
        while (low < high){
            while (low < high && x[high] >= temp){
                high --;
            }
            x[low] = x[high];
            while (low < high && x[low] <= temp){
                low ++;
            }
            x[high] = x[low];
        }
        x[low] = temp;
        return low;
    }

    //直接插入排序
    public void insertSort(int[] x){
        for (int i = 0;i < x.length;i++){
            int temp = x[i];
            int j;
            for (j = i - 1;j >= 0 && x[j] > temp;j--){
                x[j+1] = x[j];
            }
            x[j+1] = temp;
        }
    }

    //折半插入排序 适合于对于排好序的 插入一个未排序的数
    public void binaryInsertSort(int[] x){
        for (int i = 0;i < x.length;i++){
            int temp = x[i];
            int left = 0;
            int right = i - 1;
            int mid;
            while(left <= right){
                mid = (left + right)/2;
                if (temp < x[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
            for (int j = i-1;j >= left;j--){
                x[j+1] = x[j];
            }
            if (left != i){
                x[left] = temp;
            }
        }
    }

    //选择排序 通过比较未排序序列 选择最小的放到队首
    public void chooseSort(int[] x){
        int min ;
        for (int i = 0;i < x.length; i++){
            min = i;
            for (int j = i+1;j < x.length;j++){//循环未排序序列 找最小值
                if (x[j] < x[min]){
                    min = j;
                }
            }
            swap(min,i,x);
        }
    }

    //希尔排序 希尔排序是插入排序的一种优化 插入排序适用于将未排序的一个值插入已排序的
    //希尔排序将大数组分成小数组进行排序 然后再形成基本有序的大数组排序
    public void shellSort(int[] x){
        int dk = x.length/2;
        while (dk >= 1){
            shellInsert(x,dk);
            dk = dk / 2;
        }
    }

    public void shellInsert(int[] x,int dk){
        for (int i = dk;i < x.length;i++){
            int temp = x[i];
            int j;
            for (j=i-dk;j>=0 && x[j]>temp;j=j-dk){
                //将大于temp的向后移动k位
                x[j+dk] = x[j];
            }
            x[j+dk] = temp;
        }
    }

    //归并排序
    //主要采取了分开排序法 使每一个排序段都有序 然后使各个排序段归并为一个排序段 相对于快排更稳定 但需要更多的内存空间
    public int[] mergeSort(int[] x){
        if (x.length < 2) return x;
        int mid = x.length/2;
        int[] x1 = Arrays.copyOfRange(x,0,mid);
        int[] x2 = Arrays.copyOfRange(x,mid,x.length);
        //开始归并操作
        merge(mergeSort(x1),mergeSort(x2),x);
        return x;
    }

    //归并排序操作
    public void merge(int[] x1,int[] x2,int[] x){
        for (int index=0,i=0,j = 0;index < x.length;index++){
            if (i >= x1.length){
                x[index] = x2[j++];
            }else if (j >= x2.length){
                x[index] = x1[i++];
            }else if (x1[i] > x2[j]){
                x[index] = x2[j++];
            }else {
                x[index] = x1[i++];
            }
        }
    }

    //堆排序
    //利用二叉树（或者说堆的性质）通过交换堆的第一个元素（最大或最小元素）与最后一个元素 不断调整R[1..N]为新的堆 总而堆最后的值为有序的
    public void heapSort(int[] x){
        len = x.length;
        if (len < 1){
            return;
        }
        //构造堆
        buildHeap(x);
        //循环与末位交换 保证末位的是有序的
        while (len > 0){
            swap(0,len - 1,x);
            len--;
            adjustHeap(x,0);
        }
    }

    //构建堆的方法
    public void buildHeap(int[] x){
        //从最后一个叶子节点向上构造最大堆
        for (int i = (len/2-1);i >= 0;i--){
            adjustHeap(x,i);
        }
    }

    //调整堆的方法
    public void adjustHeap(int[] x,int i){
        int maxIndex = i;
        //根据左右子节点 如果子节点有更大的值 指向子节点
        if (i*2<len && x[i*2] > x[maxIndex]){
            maxIndex = i*2;
        }
        if (i*2+1<len && x[i*2+1] > x[maxIndex]){
            maxIndex = i*2+1;
        }
        //交换节点值之后并继续递归调整
        if (maxIndex != i){
            swap(maxIndex,i,x);
            adjustHeap(x,maxIndex);
        }
    }

    //交换
    public void swap(int ov,int nv,int[] x){
        int temp = x[ov];
        x[ov] = x[nv];
        x[nv] = temp;
    }
}
