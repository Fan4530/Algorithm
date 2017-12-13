package Algorithm.Interview.PocketGem;
class UpdateQueryNaive {
    private int[] nums;
    public UpdateQueryNaive(int[] nums)
    {
        this.nums = nums;
    }
    public void Update(int i, int val)
    {
        nums[i] = val;   // Update the specific element
    }
    public int SumRange(int i, int j)
    {
        int sum = 0;
        for (int low = i; low <= j; low++) //Iterate from i to j, sum from i to j
            sum += nums[low];
        return sum;
    }
}
public class UpdateQuery {

}
