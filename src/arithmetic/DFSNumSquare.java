package arithmetic;

/**
 * 四平方定理 任何一个正整数都可以表示为为4个数的平方和  ---我怎么没记得数学中学过
 * 其实这个题会有各种解法
 * 1 贪心算法 就是先用最大的尝试 如果可以的话 求出最优解 然后再继续求剩下部分的最优解 但是本题不适用 12 -> 4 4 4 而不是 9 1 1 1
 * 1-1 动态规划
 * 2 使用四平方定理 满足四数平方和定理的数n（这里要满足由四个数构成，小于四个不行），肯定除以8余7
 * 3 使用图的最短路径求解 即图的广度遍历
 */
public class DFSNumSquare {
}
