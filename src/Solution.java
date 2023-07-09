import java.util.*;

public class Solution {
    /**
     * 1. 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] sorts = {0,0};
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums.length; j++){
                int sum = nums[i] + nums[j];
                if(sum == target && i != j){
                    sorts[0] = i;
                    sorts[1] = j;
                    return sorts;
                }
            }
        }
        return sorts;
    }

    /**
     * 1. 两数之和 - 2
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        for(int j = 0; j < nums.length; j++){
            Integer keep = nums[j];
            for(int i = 0; i < nums.length; i++){
                int num = nums[i];
                int sum = 0;
                if(keep != null && i != j){
                    sum = keep + num;
                    if(sum == target){
                        int[] finalNums = {j,i};
                        return finalNums;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode finalNode = new ListNode(0);
        ListNode nodes = finalNode;
        int keepInt = 0;
        while(true){
            if(l1 == null && l2 == null && keepInt == 0){
                break;
            }
            int thisInt = 0;
            if(l1 != null){
                thisInt += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                thisInt += l2.val;
                l2 = l2.next;
            }
            thisInt += keepInt;
            keepInt = 0;
            if(thisInt > 9){
                keepInt = thisInt / 10;
                thisInt = thisInt % 10;
            }
            ListNode node = new ListNode(thisInt);
            nodes.next = node;
            nodes = nodes.next;
        }
        return finalNode.next;
    }

    /**
     * 15. 三数之和
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> finalList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int k = n - 1;
            for(int j = i + 1; j < n; j++){
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while(j < k && nums[j] + nums[k] > -nums[i]){
                    k--;
                }
                if(j == k){
                    break;
                }
                if (i != j && i != k && j != k && (nums[i]+nums[j]+nums[k]) == 0){
                    List<Integer> numsList = new ArrayList<>();
                    numsList.add(nums[i]);
                    numsList.add(nums[j]);
                    numsList.add(nums[k]);
                    finalList.add(numsList);
                }
            }
        }
        return finalList;
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumII(int[] numbers, int target) {
        for(int i = 0; i < numbers.length; i++){
            int main = numbers[i];
            for(int j = 0; j < numbers.length; j++){
                if(i != j){
                    int sub = numbers[j];
                    if((main+sub) == target){
                        int[] nums = {i+1,j+1};
                        return nums;
                    }
                }
            }
        }
        int[] nums = {};
        return nums;
    }

    /**
     * 445. 两数相加 II
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        List<Integer> l1List = new ArrayList<>();
        while(l1 != null){
            l1List.add(l1.val);
            l1 = l1.next;
        }
        List<Integer> l2List = new ArrayList<>();
        while(l2 != null){
            l2List.add(l2.val);
            l2 = l2.next;
        }
        List<Integer> mainList = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        if(l1List.size() > l2List.size()){
            mainList = l1List;
            subList = l2List;
        }else{
            mainList = l2List;
            subList = l1List;
        }
        int sizes = mainList.size() - subList.size();
        List<Integer> newSubList = new ArrayList<>();
        for(int i = 0; i < sizes; i++){
            newSubList.add(0);
        }
        newSubList.addAll(subList);
        List<Integer> finalList = new ArrayList<>();
        int keep = 0;
        int i = mainList.size()-1;
        while(true){
            if(i < 0 && keep == 0){
                break;
            }
            int sum = 0;
            if(i >= 0){
                sum = mainList.get(i) + newSubList.get(i);
            }
            sum += keep;
            if(sum > 9){
                keep = sum / 10;
                sum = sum % 10;
            }else{
                keep = 0;
            }
            finalList.add(sum);
            i--;
        }
        ListNode finalNode = new ListNode(0);
        ListNode nodes = finalNode;
        for(int j = finalList.size()-1; j >= 0; j--){
            ListNode node = new ListNode(finalList.get(j));
            nodes.next = node;
            nodes = nodes.next;
        }
        return finalNode.next;
    }

    /**
     * 1253. 重构 2 行二进制矩阵
     * @param upper
     * @param lower
     * @param colsum
     * @return
     */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> finalList = new ArrayList<>();
        int listSum = 0;
        List<Integer> upperList = new ArrayList<>(colsum.length);
        List<Integer> lowerList = new ArrayList<>(colsum.length);
        int maxVal = 0;
        for(int i = 0; i < colsum.length; i++){
            int val = colsum[i];
            listSum += val;
            upperList.add(0);
            lowerList.add(0);
            if(val != 0){
                maxVal += 1;
            }
        }
        int abSum = upper + lower;
        if(listSum != abSum || maxVal < upper || maxVal < lower){
            return finalList;
        }
        for(int j = 2; j > -1; j--){
            for(int i = 0; i < colsum.length; i++){
                int count = colsum[i];
                if(count == j){
                    if(upper > 0 && count > 0){
                        upperList.set(i,1);
                        upper -= 1;
                        count -= 1;
                    }else{
                        upperList.set(i,0);
                    }
                    if(lower > 0 && count > 0){
                        lowerList.set(i,1);
                        lower -= 1;
                    }else{
                        lowerList.set(i,0);
                    }
                }
            }
        }

        finalList.add(upperList);
        finalList.add(lowerList);
        return finalList;
    }

    /**
     * 2178. 拆分成最多数目的正偶数之和
     * @param finalSum
     * @return
     */
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> finalList = new ArrayList<>();
        if((finalSum % 2) != 0){
            return finalList;
        }
        int i = 1;
        while(true){
            long num = i * 2L;
            finalList.add(num);
            finalSum -= num;
            if(finalSum <= num){
                break;
            }
            i++;
        }
        int size = finalList.size();
        finalList.set(size-1, finalList.get(size-1) + finalSum);
        return finalList;
    }

    /**
     * 2235. 两整数相加
     * @param num1
     * @param num2
     * @return
     */
    public int sum(int num1, int num2) {
        return num1 + num2;
    }

    /**
     * 2490. 回环句
     * @param sentence
     * @return
     */
    public boolean isCircularSentence(String sentence) {
        String[] splitString = sentence.split(" ");
        for(int i = 0; i < splitString.length; i++){
            String thisString = splitString[i];
            String lastWord = thisString.substring(thisString.length() - 1);
            String nextString;
            if(i < (splitString.length - 1)){
                nextString = splitString[i + 1];
            }else{
                nextString = splitString[0];
            }
            String nextFirstWord = nextString.substring(0,1);
            if(!lastWord.equals(nextFirstWord)){
                return false;
            }
        }
        return true;
    }

    /**
     * 2600. K 件物品的最大和
     * @param numOnes
     * @param numZeros
     * @param numNegOnes
     * @param k
     * @return
     */
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int sum = 0;
        for(int i = 0; i < k; i++){
            if(numOnes > 0){
                sum += 1;
                numOnes -= 1;
            }else if(numZeros > 0){
                numZeros -= 1;
            }else if(numNegOnes > 0){
                sum -= 1;
                numNegOnes -= 1;
            }
        }
        return sum;
    }

    /**
     * 2679. 矩阵中的和
     * @param nums
     * @return
     */
    public int matrixSum(int[][] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            Arrays.sort(nums[i]);
        }
        for(int j = 0; j < nums[0].length; j++){
            int sbSum = 0;
            for(int i = 0; i < nums.length; i++){
                int num = nums[i][j];
                if(num > sbSum){
                    sbSum = num;
                }
            }
            sum += sbSum;
        }
        return sum;
    }

    /**
     * 2679. 矩阵中的和 - 2
     * @param nums
     * @return
     */
    public int matrixSum2(int[][] nums) {
        for(int i = 0; i < nums.length; i++){
            Arrays.sort(nums[i]);
        }
        Map<Integer,Integer> sbMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums[i].length; j++){
                int num = nums[i][j];
                if(!sbMap.containsKey(j)){
                    sbMap.put(j,num);
                }else if(num > sbMap.get(j)){
                    sbMap.put(j,num);
                }
            }
        }
        int sum = 0;
        for(int i = 0; i < sbMap.size(); i++){
            sum += sbMap.get(i);
        }
        return sum;
    }
}
