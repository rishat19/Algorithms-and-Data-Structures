using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;

namespace Leetcode
{
    public class Solution
    {

        // 1
        // 48. Rotate Image
        public void Rotate(int[][] matrix)
        {
            int n = matrix.Length;
            if (n == 1) return;
            for (int i = 0; i < n / 2; i++)
                for (int j = 0; j < (n + 1) / 2; j++)
                {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n - 1 - j][i];
                    matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                    matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                    matrix[j][n - 1 - i] = temp;
                }
        }

        // 2
        // 9. Palindrome Number
        public bool IsPalindrome(int x)
        {
            if (x < 0) return false;
            int z = x;
            int y = 0;
            while (z > 0)
            {
                y *= 10;
                y += z % 10;
                z /= 10;
            }
            if (x == y) return true;
            return false;
        }

        // 3
        // 12. Integer to Roman
        public string IntToRoman(int num)
        {
            int[] romanValues = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
            string[] romanCharacters = new string[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < romanValues.Length; i++)
            {
                while (num >= romanValues[i])
                {
                    num -= romanValues[i];
                    result.Append(romanCharacters[i]);
                }
            }
            return result.ToString();
        }

        // 4
        // 13. Roman to Integer
        public int RomanToInt(string s)
        {
            int result = 0;
            for (int i = 0; i < s.Length; i++)
            {
                if (s[i] == 'M') result += 1000;
                else if (s[i] == 'C')
                {
                    if (i + 1 == s.Length) result += 100;
                    else if (s[i + 1] == 'M')
                    {
                        i++;
                        result += 900;
                    }
                    else if (s[i + 1] == 'D')
                    {
                        i++;
                        result += 400;
                    }
                    else result += 100;
                }
                else if (s[i] == 'D') result += 500;
                else if (s[i] == 'X')
                {
                    if (i + 1 == s.Length) result += 10;
                    else if (s[i + 1] == 'C')
                    {
                        i++;
                        result += 90;
                    }
                    else if (s[i + 1] == 'L')
                    {
                        i++;
                        result += 40;
                    }
                    else result += 10;
                }
                else if (s[i] == 'L') result += 50;
                else if (s[i] == 'I')
                {
                    if (i + 1 == s.Length) result += 1;
                    else if (s[i + 1] == 'X')
                    {
                        i++;
                        result += 9;
                    }
                    else if (s[i + 1] == 'V')
                    {
                        i++;
                        result += 4;
                    }
                    else result += 1;
                }
                else if (s[i] == 'V') result += 5;
            }
            return result;
        }

        // 5
        // 62. Unique Paths
        public int UniquePaths(int m, int n)
        {
            if (m == 1 || n == 1) return 1;
            if (m == 2) return n;
            if (n == 2) return m;
            if (m == 3) return (1 + n) * n / 2;
            if (n == 3) return (1 + m) * m / 2;
            if (m == n) return 2 * UniquePaths(m, n - 1);
            return UniquePaths(m, n - 1) + UniquePaths(m - 1, n);
        }

        // 6
        // 2235. Add Two Integers
        public int Sum(int num1, int num2)
        {
            return num1 + num2;
        }

        // 7
        // 1828. Queries on Number of Points Inside a Circle
        public int[] CountPoints(int[][] points, int[][] queries)
        {
            int[] result = new int[queries.Length];
            for (int i = 0; i < queries.Length; i++)
            {
                int cx = queries[i][0];
                int cy = queries[i][1];
                int r = queries[i][2];
                int sum = 0;
                for (int j = 0; j < points.Length; j++)
                {
                    int x = points[j][0];
                    int y = points[j][1];
                    if ((cx - x) * (cx - x) + (cy - y) * (cy - y) <= r * r) sum++;
                }
                result[i] = sum;
            }
            return result;
        }

        // 8
        // 1512. Number of Good Pairs
        public int NumIdenticalPairs(int[] nums)
        {
            Array.Sort(nums);
            int n = 1;
            int result = 0;
            for (int i = 1; i < nums.Length; i++)
            {
                if (nums[i] == nums[i - 1]) n++;
                else
                {
                    result += (n - 1) * n / 2;
                    n = 1;
                }
            }
            result += (n - 1) * n / 2;
            return result;
        }

        // 9
        // 1551. Minimum Operations to Make Array Equal
        public int MinOperations(int n)
        {
            if (n % 2 == 1) return (n - 1) * (n / 2 + 1) / 2;
            else return n * n / 4;
        }

        // 10
        // 1281. Subtract the Product and Sum of Digits of an Integer
        public int SubtractProductAndSum(int n)
        {
            int product = 1;
            int sum = 0;
            while (n > 0)
            {
                product *= n % 10;
                sum += n % 10;
                n /= 10;
            }
            return product - sum;
        }

        // 11
        // 1689. Partitioning Into Minimum Number Of Deci-Binary Numbers
        public int MinPartitions(string n)
        {
            int max = 0;
            for (int i = 0; i < n.Length; i++)
            {
                int current = n[i] - '0';
                if (current > max) max = current;
                if (max == 9) break;
            }
            return max;
        }

        // 12
        // 1929. Concatenation of Array
        public int[] GetConcatenation(int[] nums)
        {
            int[] array = new int[2 * nums.Length];
            for (int i = 0; i < nums.Length; i++)
            {
                array[i] = nums[i];
                array[i + nums.Length] = nums[i];
            }
            return array;
        }

        // 13
        // 1769. Minimum Number of Operations to Move All Balls to Each Box
        public int[] MinOperations(string boxes)
        {
            int[] result = new int[boxes.Length];
            for (int i = 0; i < boxes.Length; i++)
            {
                int sum = 0;
                for (int j = 0; j < boxes.Length; j++)
                    if (boxes[j] == '1') sum += Math.Abs(i - j);
                result[i] = sum;
            }
            return result;
        }

        // 14
        // 1920. Build Array from Permutation
        public int[] BuildArray(int[] nums)
        {
            int[] array = new int[nums.Length];
            for (int i = 0; i < nums.Length; i++)
                array[i] = nums[nums[i]];
            return array;
        }

        // 15
        // 807. Max Increase to Keep City Skyline
        public int MaxIncreaseKeepingSkyline(int[][] grid)
        {
            int n = grid.Length;
            int[] maxi = new int[n];
            int[] maxj = new int[n];
            int result = 0;
            for (int i = 0; i < n; i++)
            {
                maxi[i] = grid[i][0];
                for (int j = 1; j < n; j++)
                    if (grid[i][j] > maxi[i]) maxi[i] = grid[i][j];
            }
            for (int j = 0; j < n; j++)
            {
                maxj[j] = grid[0][j];
                for (int i = 1; i < n; i++)
                    if (grid[i][j] > maxj[j]) maxj[j] = grid[i][j];
            }
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    result += Math.Min(maxi[i], maxj[j]) - grid[i][j];
            return result;
        }

        // 16
        // 1480. Running Sum of 1d Array
        public int[] RunningSum(int[] nums)
        {
            int[] sums = new int[nums.Length];
            sums[0] = nums[0];
            for (int i = 1; i < nums.Length; i++)
                sums[i] = sums[i - 1] + nums[i];
            return sums;
        }

        // 17
        // 1637. Widest Vertical Area Between Two Points Containing No Points
        public int MaxWidthOfVerticalArea(int[][] points)
        {
            int[] x = new int[points.Length];
            for (int i = 0; i < x.Length; i++)
                x[i] = points[i][0];
            Array.Sort(x);
            int max = 0;
            for (int i = 1; i < x.Length; i++)
                if (x[i] - x[i - 1] > max) max = x[i] - x[i - 1];
            return max;
        }

        // 18
        // 1672. Richest Customer Wealth
        public int MaximumWealth(int[][] accounts)
        {
            int max = 0;
            for (int i = 0; i < accounts.Length; i++)
            {
                int current = 0;
                for (int j = 0; j < accounts[i].Length; j++)
                    current += accounts[i][j];
                if (current > max) max = current;
            }
            return max;
        }

        // 19
        // 2120. Execution of All Suffix Instructions Staying in a Grid
        public int[] ExecuteInstructions(int n, int[] startPos, string s)
        {
            int[] result = new int[s.Length];
            for (int i = 0; i < s.Length; i++)
            {
                int x = startPos[0];
                int y = startPos[1];
                result[i] = 0;
                for (int j = i; j < s.Length; j++)
                {
                    if (s[j] == 'U') x -= 1;
                    if (s[j] == 'D') x += 1;
                    if (s[j] == 'L') y -= 1;
                    if (s[j] == 'R') y += 1;
                    if (x < 0 || y < 0 || x >= n || y >= n) break;
                    result[i]++;
                }
            }
            return result;
        }

        // 20
        // 2011. Final Value of Variable After Performing Operations
        public int FinalValueAfterOperations(string[] operations)
        {
            int result = 0;
            for (int i = 0; i < operations.Length; i++)
            {
                if (operations[i].Equals("++X") || operations[i].Equals("X++")) result++;
                if (operations[i].Equals("--X") || operations[i].Equals("X--")) result--;
            }
            return result;
        }

        // 21
        // 1409. Queries on a Permutation With Key
        public int[] ProcessQueries(int[] queries, int m)
        {
            int[] result = new int[queries.Length];
            LinkedList<int> list = new LinkedList<int>();
            for (int i = 1; i <= m; i++)
                list.AddLast(i);
            for (int i = 0; i < queries.Length; i++)
            {
                result[i] = 0;
                foreach (var element in list)
                {
                    if (element != queries[i]) result[i]++;
                    else
                    {
                        list.Remove(element);
                        list.AddFirst(element);
                        break;
                    }
                }
            }
            return result;
        }

        // 22
        // 1108. Defanging an IP Address
        public string DefangIPaddr(string address)
        {
            return address.Replace(".", "[.]");
        }

        // 23
        // 2161. Partition Array According to Given Pivot
        public int[] PivotArray(int[] nums, int pivot)
        {
            List<int> before = new List<int>();
            int equal = 0;
            List<int> after = new List<int>();
            for (int i = 0; i < nums.Length; i++)
            {
                if (nums[i] < pivot) before.Add(nums[i]);
                if (nums[i] == pivot) equal++;
                if (nums[i] > pivot) after.Add(nums[i]);
            }
            int[] result = new int[nums.Length];
            int j = 0;
            foreach (int num in before)
            {
                result[j] = num;
                j++;
            }
            while (equal > 0)
            {
                result[j] = pivot;
                equal--;
                j++;
            }
            foreach (int num in after)
            {
                result[j] = num;
                j++;
            }
            return result;
        }

        // 24
        // 2114. Maximum Number of Words Found in Sentences
        public int MostWordsFound(string[] sentences)
        {
            int max = 0;
            for (int i = 0; i < sentences.Length; i++)
            {
                int current = sentences[i].Split(" ").Length;
                if (current > max) max = current;
            }
            return max;
        }

        // 25
        // 2149. Rearrange Array Elements by Sign
        public int[] RearrangeArray(int[] nums)
        {
            List<int> positive = new List<int>();
            List<int> negative = new List<int>();
            int[] result = new int[nums.Length];
            for (int i = 0; i < nums.Length; i++)
            {
                if (nums[i] > 0) positive.Add(nums[i]);
                if (nums[i] < 0) negative.Add(nums[i]);
            }
            for (int i = 0; i < positive.Count; i++)
                result[2 * i] = positive[i];
            for (int i = 0; i < negative.Count; i++)
                result[2 * i + 1] = negative[i];
            return result;
        }

        // 26
        // 1470. Shuffle the Array
        public int[] Shuffle(int[] nums, int n)
        {
            int[] array = new int[2 * n];
            for (int i = 0; i < n; i++)
                array[2 * i] = nums[i];
            for (int i = 0; i < n; i++)
                array[2 * i + 1] = nums[i + n];
            return array;
        }

        // 27
        // 1329. Sort the Matrix Diagonally
        public int[][] DiagonalSort(int[][] mat)
        {
            int[][] matrix = new int[mat.Length][];
            for (int i = 0; i < matrix.Length; i++)
                matrix[i] = new int[mat[i].Length];
            for (int k = 0; k < mat.Length; k++)
            {
                List<int> list = new List<int>();
                int i = k;
                int j = 0;
                while (i < mat.Length && j < mat[0].Length)
                {
                    list.Add(mat[i][j]);
                    i++;
                    j++;
                }
                list.Sort();
                i = k;
                j = 0;
                while (i < mat.Length && j < mat[0].Length)
                {
                    matrix[i][j] = list[j];
                    i++;
                    j++;
                }
            }
            for (int k = 1; k < mat[0].Length; k++)
            {
                List<int> list = new List<int>();
                int i = 0;
                int j = k;
                while (i < mat.Length && j < mat[0].Length)
                {
                    list.Add(mat[i][j]);
                    i++;
                    j++;
                }
                list.Sort();
                i = 0;
                j = k;
                while (i < mat.Length && j < mat[0].Length)
                {
                    matrix[i][j] = list[i];
                    i++;
                    j++;
                }
            }
            return matrix;
        }

        // 28
        // 2160. Minimum Sum of Four Digit Number After Splitting Digits
        public int MinimumSum(int num)
        {
            int[] digits = new int[4];
            int i = 0;
            while (num > 0)
            {
                digits[i] = num % 10;
                num /= 10;
                i++;
            }
            Array.Sort(digits);
            return digits[0] * 10 + digits[1] * 10 + digits[2] + digits[3];
        }

        // 29
        // 1817. Finding the Users Active Minutes
        public int[] FindingUsersActiveMinutes(int[][] logs, int k)
        {
            Dictionary<int, HashSet<int>> map = new Dictionary<int, HashSet<int>>();
            for (int i = 0; i < logs.Length; i++)
                if (!map.ContainsKey(logs[i][0])) map.Add(logs[i][0], new HashSet<int>());
            for (int i = 0; i < logs.Length; i++)
                map[logs[i][0]].Add(logs[i][1]);
            int[] result = new int[k];
            foreach (KeyValuePair<int, HashSet<int>> entry in map)
                result[entry.Value.Count - 1]++;
            return result;
        }

        // 30
        // 771. Jewels and Stones
        public int NumJewelsInStones(string jewels, string stones)
        {
            int result = 0;
            for (int i = 0; i < stones.Length; i++)
                for (int j = 0; j < jewels.Length; j++)
                    if (stones[i] == jewels[j])
                    {
                        result++;
                        break;
                    }
            return result;
        }

        // 31
        // 1877. Minimize Maximum Pair Sum in Array
        public int MinPairSum(int[] nums)
        {
            Array.Sort(nums);
            int max = 0;
            for (int i = 0; i < nums.Length / 2; i++)
            {
                int pairSum = nums[i] + nums[nums.Length - 1 - i];
                if (pairSum > max) max = pairSum;
            }
            return max;
        }

        // 32
        // 1431. Kids With the Greatest Number of Candies
        public IList<bool> KidsWithCandies(int[] candies, int extraCandies)
        {
            int max = candies[0];
            for (int i = 1; i < candies.Length; i++)
                if (candies[i] > max) max = candies[i];
            List<bool> result = new List<bool>();
            for (int i = 0; i < candies.Length; i++)
                if (candies[i] + extraCandies >= max) result.Add(true);
                else result.Add(false);
            return result;
        }

        // 33
        // 2079. Watering Plants
        public int WateringPlants(int[] plants, int capacity)
        {
            int steps = 0;
            int size = capacity;
            for (int i = 0; i < plants.Length; i++)
            {
                if (size >= plants[i])
                {
                    steps += 1;
                    size -= plants[i];
                }
                else if (capacity >= plants[i])
                {
                    steps += 2 * i + 1;
                    size = capacity - plants[i];
                }
                else break;
            }
            return steps;
        }

        // 34
        // 1365. How Many Numbers Are Smaller Than the Current Number
        public int[] SmallerNumbersThanCurrent(int[] nums)
        {
            int[] sortedNums = new int[nums.Length];
            for (int i = 0; i < nums.Length; i++)
                sortedNums[i] = nums[i];
            Array.Sort(sortedNums);
            int[] result = new int[nums.Length];
            for (int i = 0; i < nums.Length; i++)
                result[i] = Array.IndexOf(sortedNums, nums[i]);
            return result;
        }

        // 35
        // 763. Partition Labels
        public IList<int> PartitionLabels(string s)
        {
            Dictionary<char, int> map = new Dictionary<char, int>();
            for (int i = 0; i < s.Length; i++)
                if (!map.ContainsKey(s[i])) map.Add(s[i], i);
                else map[s[i]] = i;
            List<int> result = new List<int>();
            int lastPartIndex = 0;
            int current = map[s[0]];
            for (int i = 0; i < s.Length; i++)
            {
                int index = map[s[i]];
                if (index > current) current = index;
                if (current <= i)
                {
                    result.Add(current - lastPartIndex + 1);
                    lastPartIndex = i + 1;
                    if (i + 1 < s.Length) current = map[s[i + 1]];
                }
            }
            return result;
        }

        // 36
        // 1720. Decode XORed Array
        public int[] Decode(int[] encoded, int first)
        {
            int[] result = new int[encoded.Length + 1];
            result[0] = first;
            for (int i = 1; i < result.Length; i++)
                result[i] = result[i - 1] ^ encoded[i - 1];
            return result;
        }

        // 37
        // 2221. Find Triangular Sum of an Array
        public int TriangularSum(int[] nums)
        {
            while (nums.Length > 1)
            {
                int[] temp = new int[nums.Length - 1];
                for (int i = 0; i < nums.Length - 1; i++)
                    temp[i] = (nums[i] + nums[i + 1]) % 10;
                nums = temp;
            }
            return nums[0];
        }

        // 38
        // 1313. Decompress Run-Length Encoded List
        public int[] DecompressRLElist(int[] nums)
        {
            List<int> result = new List<int>();
            for (int i = 0; i < nums.Length; i += 2)
            {
                int freq = nums[i];
                int val = nums[i + 1];
                for (int j = 0; j < freq; j++)
                    result.Add(val);
            }
            return result.ToArray();
        }

        // 39
        // 1282. Group the People Given the Group Size They Belong To
        public IList<IList<int>> GroupThePeople(int[] groupSizes)
        {
            Dictionary<int, List<int>> map = new Dictionary<int, List<int>>();
            for (int i = 0; i < groupSizes.Length; i++)
            {
                if (!map.ContainsKey(groupSizes[i])) map.Add(groupSizes[i], new List<int>());
                map[groupSizes[i]].Add(i);
            }
            List<IList<int>> result = new List<IList<int>>();
            foreach (KeyValuePair<int, List<int>> entry in map)
            {

                for (int i = 0; i < entry.Value.Count; i += entry.Key)
                {
                    IList<int> group = new List<int>();
                    for (int j = i; j < i + entry.Key; j++)
                        group.Add(entry.Value[j]);
                    result.Add(group);
                }
            }
            return result;
        }

        // 40
        // 1678. Goal Parser Interpretation
        public string Interpret(string command)
        {
            return command.Replace("()", "o").Replace("(", "").Replace(")", "");
        }

        // 41
        // 1630. Arithmetic Subarrays
        public IList<bool> CheckArithmeticSubarrays(int[] nums, int[] l, int[] r)
        {
            List<bool> result = new List<bool>();
            for (int i = 0; i < l.Length; i++)
            {
                var slice = nums.Skip(l[i]).Take(r[i] - l[i] + 1).ToArray();
                Array.Sort(slice);
                int d = slice[1] - slice[0];
                bool answer = true;
                for (int j = 2; j < slice.Length; j++)
                    if (slice[j] - slice[j - 1] != d)
                    {
                        answer = false;
                        break;
                    }
                result.Add(answer);
            }
            return result;
        }

        // 42
        // 1528. Shuffle String
        public string RestoreString(string s, int[] indices)
        {
            char[] array = new char[indices.Length];
            for (int i = 0; i < indices.Length; i++)
                array[indices[i]] = s[i];
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < array.Length; i++)
                result.Append(array[i]);
            return result.ToString();
        }

        // 43
        // 1605. Find Valid Matrix Given Row and Column Sums
        public int[][] RestoreMatrix(int[] rowSum, int[] colSum)
        {
            int n = rowSum.Length;
            int m = colSum.Length;
            int[][] matrix = new int[n][];
            for (int i = 0; i < n; i++)
                matrix[i] = new int[m];
            int k = Math.Min(n, m);
            for (int i = 0; i < k; i++)
            {
                int min = Math.Min(rowSum[i], colSum[i]);
                matrix[i][i] = min;
                rowSum[i] -= min;
                colSum[i] -= min;
            }
            for (int i = 0; i < k; i++)
            {
                if (rowSum[i] == 0)
                {
                    for (int j = i; j < n && colSum[i] > 0; j++)
                        if (rowSum[j] > 0)
                        {
                            int min = Math.Min(rowSum[j], colSum[i]);
                            matrix[j][i] = min;
                            rowSum[j] -= min;
                            colSum[i] -= min;
                        }
                }
                else
                {
                    for (int j = i; j < m && rowSum[i] > 0; j++)
                        if (colSum[j] > 0)
                        {
                            int min = Math.Min(colSum[j], rowSum[i]);
                            matrix[i][j] = min;
                            colSum[j] -= min;
                            rowSum[i] -= min;
                        }
                }
            }
            return matrix;
        }

        // 44
        // 1389. Create Target Array in the Given Order
        public int[] CreateTargetArray(int[] nums, int[] index)
        {
            List<int> list = new List<int>();
            for (int i = 0; i < index.Length; i++)
                list.Insert(index[i], nums[i]);
            return list.ToArray();
        }

        // 45
        // 1561. Maximum Number of Coins You Can Get
        public int MaxCoins(int[] piles)
        {
            Array.Sort(piles);
            int sum = 0;
            for (int i = piles.Length - 2; i >= piles.Length / 3; i -= 2)
                sum += piles[i];
            return sum;
        }

        // 46
        // 1342. Number of Steps to Reduce a Number to Zero
        public int NumberOfSteps(int num)
        {
            int steps = 0;
            while (num > 0)
            {
                if (num % 2 == 0) num /= 2;
                else num -= 1;
                steps++;
            }
            return steps;
        }

        // 47
        // 950. Reveal Cards In Increasing Order
        public int[] DeckRevealedIncreasing(int[] deck)
        {
            Array.Sort(deck);
            Queue<int> queue = new Queue<int>();
            queue.Enqueue(deck[deck.Length - 1]);
            for (int i = deck.Length - 2; i >= 0; i--)
            {
                queue.Enqueue(queue.Dequeue());
                queue.Enqueue(deck[i]);
            }
            int[] result = queue.ToArray();
            Array.Reverse(result);
            return result;
        }

        // 48
        // 2194. Cells in a Range on an Excel Sheet
        public IList<string> CellsInRange(string s)
        {
            List<string> result = new List<string>();
            for (int i = s[0]; i <= s[3]; i++)
                for (int j = s[1]; j <= s[4]; j++)
                {
                    char letter = (char)i;
                    char digit = (char)j;
                    result.Add(new StringBuilder().Append(letter).Append(digit).ToString());
                }
            return result;
        }

        // 49
        // 921. Minimum Add to Make Parentheses Valid
        public int MinAddToMakeValid(string s)
        {
            int counter = 0;
            int result = 0;
            for (int i = 0; i < s.Length; i++)
            {
                if (s[i] == '(') counter++;
                else counter--;
                if (counter < 0)
                {
                    result++;
                    counter = 0;
                }
            }
            result += counter;
            return result;
        }

        // 50
        // 1221. Split a String in Balanced Strings
        public int BalancedStringSplit(string s)
        {
            int counter = 0;
            int result = 0;
            for (int i = 0; i < s.Length; i++)
            {
                if (s[i] == 'R') counter++;
                else counter--;
                if (counter == 0) result++;
            }
            return result;
        }

        // 51
        // 1347. Minimum Number of Steps to Make Two Strings Anagram
        public int MinSteps(string s, string t)
        {
            int[] sArray = new int[26];
            int[] tArray = new int[26];
            for (int i = 0; i < s.Length; i++)
            {
                sArray[s[i] - 'a']++;
                tArray[t[i] - 'a']++;
            }
            int result = 0;
            for (int i = 0; i < 26; i++)
                if (sArray[i] > tArray[i]) result += sArray[i] - tArray[i];
            return result;
        }

        // 52
        // 1859. Sorting the Sentence
        public string SortSentence(string s)
        {
            string[] words = s.Split(" ");
            for (int i = 0; i < words.Length; i++)
                words[i] = String.Concat(words[i].Reverse());
            Array.Sort(words);
            for (int i = 0; i < words.Length; i++)
                words[i] = String.Concat(words[i].Substring(1).Reverse());
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < words.Length - 1; i++)
                result.Append(words[i]).Append(" ");
            result.Append(words[words.Length - 1]);
            return result.ToString();
        }

        // 53
        // 1823. Find the Winner of the Circular Game
        public int FindTheWinner(int n, int k)
        {
            List<int> list = new List<int>();
            for (int i = 1; i <= n; i++)
                list.Add(i);
            int startIndex = 0;
            while (list.Count > 1)
            {
                int r = k % list.Count;
                if (k % list.Count == 0) r = list.Count;
                int index = (startIndex + r - 1) % list.Count;
                list.RemoveAt(index);
                startIndex = index % list.Count;
            }
            return list[0];
        }

        // 54
        // 1773. Count Items Matching a Rule
        public int CountMatches(IList<IList<string>> items, string ruleKey, string ruleValue)
        {
            int result = 0;
            int index;
            if (ruleKey.Equals("type")) index = 0;
            else if (ruleKey.Equals("color")) index = 1;
            else index = 2;
            for (int i = 0; i < items.Count; i++)
                if (items[i][index].Equals(ruleValue)) result++;
            return result;
        }

        // 55
        // 1829. Maximum XOR for Each Query
        public int[] GetMaximumXor(int[] nums, int maximumBit)
        {
            int[] result = new int[nums.Length];
            int xor = (1 << maximumBit) - 1;
            for (int i = 0; i < nums.Length; i++)
            {
                xor ^= nums[i];
                result[result.Length - i - 1] = xor;
            }
            return result;
        }

        // 56
        // 1486. XOR Operation in an Array
        public int XorOperation(int n, int start)
        {
            int xor = start;
            for (int i = 1; i < n; i++)
                xor ^= (2 * i + start);
            return xor;
        }

        // 57
        // 890. Find and Replace Pattern
        public IList<string> FindAndReplacePattern(string[] words, string pattern)
        {
            List<string> result = new List<string>();
            for (int j = 0; j < words.Length; j++)
            {
                HashSet<char> set = new HashSet<char>();
                if (words[j].Length == pattern.Length)
                {
                    Dictionary<char, int> map = new Dictionary<char, int>();
                    bool flag = true;
                    for (int i = 0; i < words[j].Length; i++)
                    {
                        if (!map.ContainsKey(pattern[i])) map.Add(pattern[i], pattern[i] - words[j][i]);
                        else if (map[pattern[i]] != pattern[i] - words[j][i]) flag = false;
                        set.Add(words[j][i]);
                    }
                    if (flag && map.Count == set.Count) result.Add(words[j]);
                }
            }
            return result;
        }

        // 58
        // 1791. Find Center of Star Graph
        public int FindCenter(int[][] edges)
        {
            int a = edges[0][0];
            int b = edges[0][1];
            if (edges[1][0] == a || edges[1][0] == b) return edges[1][0];
            return edges[1][1];
        }

        // 59
        // 1314. Matrix Block Sum
        public int[][] MatrixBlockSum(int[][] mat, int k)
        {
            int[][] matrix = new int[mat.Length][];
            int[][] d = new int[mat.Length][];
            for (int i = 0; i < mat.Length; i++)
            {
                matrix[i] = new int[mat[0].Length];
                d[i] = new int[mat[0].Length];
            }
            d[0][0] = mat[0][0];
            for (int i = 1; i < d.Length; i++)
                d[i][0] = d[i - 1][0] + mat[i][0];
            for (int j = 1; j < d[0].Length; j++)
                d[0][j] = d[0][j - 1] + mat[0][j];
            for (int i = 1; i < d.Length; i++)
                for (int j = 1; j < d[0].Length; j++)
                    d[i][j] = d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1] + mat[i][j];
            for (int i = 0; i < matrix.Length; i++)
                for (int j = 0; j < matrix[0].Length; j++)
                {
                    int leftR = Math.Max(i - k, 0);
                    int leftC = Math.Max(j - k, 0);
                    int rightR = Math.Min(i + k, mat.Length - 1);
                    int rightC = Math.Min(j + k, mat[0].Length - 1);
                    matrix[i][j] = d[rightR][rightC];
                    if (leftR > 0 && leftC > 0) matrix[i][j] = matrix[i][j] - d[leftR - 1][rightC] - d[rightR][leftC - 1] + d[leftR - 1][leftC - 1];
                    else if (leftR > 0) matrix[i][j] = matrix[i][j] - d[leftR - 1][rightC];
                    else if (leftC > 0) matrix[i][j] = matrix[i][j] - d[rightR][leftC - 1];
                }
            return matrix;
        }

        // 60
        // 1588. Sum of All Odd Length Subarrays
        public int SumOddLengthSubarrays(int[] arr)
        {
            int sum = 0;
            for (int i = 0; i < arr.Length; i++)
                for (int j = i; j < arr.Length; j++)
                    if ((j - i) % 2 == 0)
                        for (int k = i; k <= j; k++)
                            sum += arr[k];
            return sum;
        }

        // 61
        // 1641. Count Sorted Vowel Strings
        public int CountVowelStrings(int n)
        {
            return CVSR(n, 5);
        }
        public int CVSR(int n, int lastChar)
        {
            if (n == 1) return lastChar;
            else if (lastChar == 1) return CVSR(n - 1, 1);
            else if (lastChar == 2) return CVSR(n - 1, 1) + CVSR(n - 1, 2);
            else if (lastChar == 3) return CVSR(n - 1, 1) + CVSR(n - 1, 2) + CVSR(n - 1, 3);
            else if (lastChar == 4) return CVSR(n - 1, 1) + CVSR(n - 1, 2) + CVSR(n - 1, 3) + CVSR(n - 1, 4);
            else return CVSR(n - 1, 1) + CVSR(n - 1, 2) + CVSR(n - 1, 3) + CVSR(n - 1, 4) + CVSR(n - 1, 5);
        }

        // 62
        // 2006. Count Number of Pairs With Absolute Difference K
        public int CountKDifference(int[] nums, int k)
        {
            int result = 0;
            for (int i = 0; i < nums.Length - 1; i++)
                for (int j = i + 1; j < nums.Length; j++)
                    if (Math.Abs(nums[i] - nums[j]) == k)
                        result++;
            return result;
        }

        // 63
        // 861. Score After Flipping Matrix
        public int MatrixScore(int[][] grid)
        {
            for (int i = 0; i < grid.Length; i++)
                if (grid[i][0] == 0)
                    for (int j = 0; j < grid[0].Length; j++)
                    {
                        if (grid[i][j] == 0) grid[i][j] = 1;
                        else grid[i][j] = 0;
                    }
            int result = 0;
            result += (1 << (grid[0].Length - 1)) * grid.Length;
            for (int j = 1; j < grid[0].Length; j++)
            {
                int zeros = 0;
                int ones = 0;
                for (int i = 0; i < grid.Length; i++)
                {
                    if (grid[i][j] == 0) zeros++;
                    else grid[i][j] = ones++;
                }
                result += (1 << (grid[0].Length - 1 - j)) * Math.Max(zeros, ones);
            }
            return result;
        }

        // 64
        // 1688. Count of Matches in Tournament
        public int NumberOfMatches(int n)
        {
            int count = 0;
            while (n >= 2)
            {
                count += n / 2;
                if (n % 2 == 0) n /= 2;
                else n = n / 2 + 1;
            }
            return count;
        }

        // 65
        // 1442. Count Triplets That Can Form Two Arrays of Equal XOR
        public int CountTriplets(int[] arr)
        {
            int[] xor = new int[arr.Length];
            xor[0] = arr[0];
            for (int i = 1; i < arr.Length; i++)
                xor[i] = xor[i - 1] ^ arr[i];
            int result = 0;
            for (int i = 0; i < arr.Length - 1; i++)
                for (int j = i + 1; j < arr.Length; j++)
                {
                    if (i == 0)
                    {
                        if (xor[j] == 0) result += j;
                    }
                    else if (xor[j] == xor[i - 1]) result += j - i;
                }
            return result;
        }

        // 66
        // 2037. Minimum Number of Moves to Seat Everyone
        public int MinMovesToSeat(int[] seats, int[] students)
        {
            Array.Sort(seats);
            Array.Sort(students);
            int result = 0;
            for (int i = 0; i < seats.Length; i++)
                result += Math.Abs(seats[i] - students[i]);
            return result;
        }

        // 67
        // 2044. Count Number of Maximum Bitwise-OR Subsets
        public int CountMaxOrSubsets(int[] nums)
        {
            int result = 0;
            int maxOr = nums[0];
            for (int i = 0; i < nums.Length; i++)
                maxOr |= nums[i];
            var sub = new List<List<int>>();
            for (int mask = 0; mask < (1 << nums.Length); mask++)
            {
                sub.Add(new List<int>());
                for (int j = 0; j < nums.Length; j++)
                    if ((mask & (1 << j)) != 0)
                        sub.Last().Add(j);
            }
            foreach (List<int> list in sub)
            {
                int oR = 0;
                for (int i = 0; i < list.Count; i++)
                    oR |= nums[list[i]];
                if (oR == maxOr) result++;
            }
            return result;
        }

        // 68
        // 1662. Check If Two String Arrays are Equivalent
        public bool ArrayStringsAreEqual(string[] word1, string[] word2)
        {
            StringBuilder w1 = new StringBuilder();
            StringBuilder w2 = new StringBuilder();
            for (int i = 0; i < word1.Length; i++)
                w1.Append(word1[i]);
            for (int i = 0; i < word2.Length; i++)
                w2.Append(word2[i]);
            if (w1.ToString().Equals(w2.ToString())) return true;
            return false;
        }

        // 69
        // 1104. Path In Zigzag Labelled Binary Tree
        public IList<int> PathInZigZagTree(int label)
        {
            int height = 0;
            int temp = label;
            while (temp > 1)
            {
                temp = temp >> 1;
                height++;
            }
            int[] result = new int[height + 1];
            result[result.Length - 1] = label;
            for (int i = result.Length - 2; i >= 0; i--)
            {
                height--;
                label = (2 << height) - 1 - label / 2 + (1 << height);
                result[i] = label;
            }
            return result;
        }

        // 70
        // 1684. Count the Number of Consistent Strings
        public int CountConsistentStrings(string allowed, string[] words)
        {
            int result = 0;
            List<char> forbidden = new List<char>();
            for (int i = 0; i < 26; i++)
                forbidden.Add((char)('a' + i));
            for (int i = 0; i < allowed.Length; i++)
                forbidden.Remove(allowed[i]);
            for (int j = 0; j < words.Length; j++)
            {
                bool flag = true;
                for (int i = 0; i < words[j].Length; i++)
                    if (forbidden.Contains(words[j][i]))
                    {
                        flag = false;
                        break;
                    }
                if (flag) result++;
            }
            return result;
        }

        // 71
        // 811. Subdomain Visit Count
        public IList<string> SubdomainVisits(string[] cpdomains)
        {
            Dictionary<string, int> map = new Dictionary<string, int>();
            for (int i = 0; i < cpdomains.Length; i++)
            {
                string[] visitsAndAddress = cpdomains[i].Split(" ");
                int visits = Int32.Parse(visitsAndAddress[0]);
                string[] addressParts = visitsAndAddress[1].Split(".");
                if (map.ContainsKey(addressParts[addressParts.Length - 1])) map[addressParts[addressParts.Length - 1]] += visits;
                else map.Add(addressParts[addressParts.Length - 1], visits);
                if (map.ContainsKey(visitsAndAddress[1])) map[visitsAndAddress[1]] += visits;
                else map.Add(visitsAndAddress[1], visits);
                if (addressParts.Length == 3)
                {
                    string domain = new StringBuilder().Append(addressParts[1]).Append(".").Append(addressParts[2]).ToString();
                    if (map.ContainsKey(domain)) map[domain] += visits;
                    else map.Add(domain, visits);
                }
            }
            List<string> result = new List<string>();
            foreach (KeyValuePair<string, int> entry in map)
                result.Add(new StringBuilder().Append(entry.Value).Append(" ").Append(entry.Key).ToString());
            return result;
        }

        // 72
        // 2103. Rings and Rods
        public int CountPoints(string rings)
        {
            List<HashSet<char>> list = new List<HashSet<char>>();
            for (int i = 0; i < 10; i++)
                list.Add(new HashSet<char>());
            for (int i = 0; i < rings.Length; i += 2)
                list[Int32.Parse(rings[i + 1].ToString())].Add(rings[i]);
            int result = 0;
            for (int i = 0; i < 10; i++)
                if (list[i].Count == 3) result++;
            return result;
        }

        // 73
        // 419. Battleships in a Board
        public int CountBattleships(char[][] board)
        {
            bool[][] visited = new bool[board.Length][];
            for (int i = 0; i < board.Length; i++)
                visited[i] = new bool[board[0].Length];
            int count = 0;
            for (int i = 0; i < board.Length; i++)
                for (int j = 0; j < board[i].Length; j++)
                    if (!visited[i][j])
                    {
                        visited[i][j] = true;
                        if (board[i][j] == 'X')
                        {
                            count++;
                            for (int k = i + 1; k < board.Length; k++)
                            {
                                if (board[k][j] == '.') break;
                                visited[k][j] = true;
                            }
                            for (int k = j + 1; k < board[0].Length; k++)
                            {
                                if (board[i][k] == '.') break;
                                visited[i][k] = true;
                            }
                        }
                    }
            return count;
        }

        // 74
        // 1832. Check if the Sentence Is Pangram
        public bool CheckIfPangram(string sentence)
        {
            HashSet<char> letters = new HashSet<char>();
            for (int i = 0; i < sentence.Length; i++)
                letters.Add(sentence[i]);
            if (letters.Count == 26) return true;
            return false;
        }

        // 75
        // 2023. Number of Pairs of Strings With Concatenation Equal to Target
        public int NumOfPairs(string[] nums, string target)
        {
            int count = 0;
            for (int i = 0; i < nums.Length - 1; i++)
                for (int j = i + 1; j < nums.Length; j++)
                {
                    if ((nums[i] + nums[j]).Equals(target)) count++;
                    if ((nums[j] + nums[i]).Equals(target)) count++;
                }
            return count;
        }

        // 76
        // 709. To Lower Case
        public string ToLowerCase(string s)
        {
            return s.ToLowerInvariant();
        }

        // 77
        // 1418. Display Table of Food Orders in a Restaurant
        public IList<IList<string>> DisplayTable(IList<IList<string>> orders)
        {
            SortedSet<string> food = new SortedSet<string>();
            SortedSet<int> tables = new SortedSet<int>();
            Dictionary<string, int> map = new Dictionary<string, int>();
            for (int i = 0; i < orders.Count; i++)
            {
                food.Add(orders[i][2]);
                tables.Add(Int32.Parse(orders[i][1]));
                string key = orders[i][2] + orders[i][1];
                if (map.ContainsKey(key)) map[key] += 1;
                else map.Add(key, 1);
            }
            string[][] result = new string[tables.Count + 1][];
            for (int i = 0; i < result.Length; i++)
                result[i] = new string[food.Count + 1];
            result[0][0] = "Table";
            int k = 1;
            foreach (int table in tables)
            {
                result[k][0] = table.ToString();
                k++;
            }
            k = 1;
            List<string> fd = new List<string>(food);
            fd.Sort(String.CompareOrdinal);
            foreach (string f in fd)
            {
                result[0][k] = f;
                k++;
            }
            for (int i = 1; i < result.Length; i++)
                for (int j = 1; j < result[0].Length; j++)
                {
                    string key = result[0][j] + result[i][0];
                    if (map.ContainsKey(key)) result[i][j] = map[key].ToString();
                    else result[i][j] = "0";
                }
            return result;
        }

        // 78
        // 1816. Truncate Sentence
        public string TruncateSentence(string s, int k)
        {
            string[] words = s.Split(" ");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < k - 1; i++)
                result.Append(words[i]).Append(" ");
            result.Append(words[k - 1]);
            return result.ToString();
        }

        // 79
        // 885. Spiral Matrix III
        public int[][] SpiralMatrixIII(int rows, int cols, int rStart, int cStart)
        {
            int[][] matrix = new int[rows][];
            for (int i = 0; i < rows; i++)
                matrix[i] = new int[cols];
            int[][] coordinates = new int[rows * cols][];
            for (int i = 0; i < coordinates.Length; i++)
                coordinates[i] = new int[2];
            int r = rStart;
            int c = cStart;
            matrix[r][c] = 1;
            coordinates[0][0] = r;
            coordinates[0][1] = c;
            int rule = 1;
            List<int> tempTurns = new List<int>();
            tempTurns.Add(1);
            int temp = 1;
            for (int i = 3; i < rows * cols * 4; i += 2)
            {
                temp += i;
                tempTurns.Add(temp);
            }
            tempTurns.Add(2);
            temp = 2;
            for (int i = 4; i < rows * cols * 4; i += 2)
            {
                temp += i;
                tempTurns.Add(temp);
            }
            tempTurns.Sort();
            Queue<int> turns = new Queue<int>();
            for (int i = 0; i < tempTurns.Count; i++)
                turns.Enqueue(tempTurns[i]);
            int k = 1;
            for (int i = 2; i <= rows * cols; i++)
            {
                switch (rule)
                {
                    case 1:
                        c += 1;
                        if (turns.Peek() == k)
                        {
                            rule = 2;
                            turns.Dequeue();
                        }
                        break;
                    case 2:
                        r += 1;
                        if (turns.Peek() == k)
                        {
                            rule = 3;
                            turns.Dequeue();
                        }
                        break;
                    case 3:
                        c -= 1;
                        if (turns.Peek() == k)
                        {
                            rule = 4;
                            turns.Dequeue();
                        }
                        break;
                    case 4:
                        r -= 1;
                        if (turns.Peek() == k)
                        {
                            rule = 1;
                            turns.Dequeue();
                        }
                        break;
                }
                k++;
                if (r >= 0 && r < rows && c >= 0 && c < cols && matrix[r][c] == 0)
                {
                    matrix[r][c] = i;
                    coordinates[i - 1][0] = r;
                    coordinates[i - 1][1] = c;
                }
                else i--;
            }
            return coordinates;
        }

        // 80
        // 1913. Maximum Product Difference Between Two Pairs
        public int MaxProductDifference(int[] nums)
        {
            Array.Sort(nums);
            return nums[nums.Length - 1] * nums[nums.Length - 2] - nums[1] * nums[0];
        }

        // 81
        // 442. Find All Duplicates in an Array
        public IList<int> FindDuplicates(int[] nums)
        {
            List<int> result = new List<int>();
            for (int i = 0; i < nums.Length; i++)
            {
                int index = Math.Abs(nums[i]) - 1;
                if (nums[index] < 0)
                    result.Add(Math.Abs(index + 1));
                nums[index] = -nums[index];
            }
            return result;
        }

        // 82
        // 2220. Minimum Bit Flips to Convert Number
        public int MinBitFlips(int start, int goal)
        {
            int result = 0;
            int xor = start ^ goal;
            string binaryCode = Convert.ToString(xor, 2);
            for (int i = 0; i < binaryCode.Length; i++)
                if (binaryCode[i] == '1') result++;
            return result;
        }

        // 83
        // 1529. Minimum Suffix Flips
        public int MinFlips(string target)
        {
            int result = 0;
            char current = '0';
            for (int i = 0; i < target.Length; i++)
            {
                if (current != target[i]) result++;
                if (result % 2 == 1) current = '1';
                else current = '0';
            }
            return result;
        }

        // 84
        // 1534. Count Good Triplets
        public int CountGoodTriplets(int[] arr, int a, int b, int c)
        {
            int result = 0;
            for (int i = 0; i < arr.Length - 2; i++)
                for (int j = i + 1; j < arr.Length - 1; j++)
                    for (int k = j + 1; k < arr.Length; k++)
                    {
                        int x = Math.Abs(arr[i] - arr[j]);
                        int y = Math.Abs(arr[j] - arr[k]);
                        int z = Math.Abs(arr[i] - arr[k]);
                        if (x <= a && y <= b && z <= c) result++;
                    }
            return result;
        }

        // 85
        // 1910. Remove All Occurrences of a Substring
        public string RemoveOccurrences(string s, string part)
        {
            while (true)
            {
                int index = s.IndexOf(part);
                if (index == -1) break;
                s = s.Remove(index, part.Length).Insert(index, "");
            }
            return s;
        }

        // 86
        // 2176. Count Equal and Divisible Pairs in an Array
        public int CountPairs(int[] nums, int k)
        {
            int count = 0;
            for (int i = 0; i < nums.Length - 1; i++)
                for (int j = i + 1; j < nums.Length; j++)
                    if (nums[i] == nums[j] && (i * j) % k == 0) count++;
            return count;
        }

        // 87
        // 1638. Count Substrings That Differ by One Character
        public int CountSubstrings(string s, string t)
        {
            int result = 0;
            for (int i = 0; i < s.Length; i++)
                for (int j = 0; j < t.Length; j++)
                {
                    int miss = 0;
                    for (int k = 0; i + k < s.Length && j + k < t.Length; k++)
                    {
                        if (s[i + k] != t[j + k] && ++miss > 1) break;
                        result += miss;
                    }
                }
            return result;
        }

        // 88
        // 804. Unique Morse Code Words
        public int UniqueMorseRepresentations(string[] words)
        {
            string[] codes = new string[] { ".-", "-...", "-.-.", "-..", ".", "..-.", 
                "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", 
                "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
            HashSet<string> set = new HashSet<string>();
            for (int i = 0; i < words.Length; i++)
            {
                StringBuilder code = new StringBuilder();
                for (int j = 0; j < words[i].Length; j++)
                    code.Append(codes[words[i][j] - 'a']);
                set.Add(code.ToString());
            }
            return set.Count;
        }

        // 89
        // 78. Subsets
        public IList<IList<int>> Subsets(int[] nums)
        {
            IList<IList<int>> subsets = new List<IList<int>>();
            for (int mask = 0; mask < (1 << nums.Length); mask++)
            {
                subsets.Add(new List<int>());
                for (int j = 0; j < nums.Length; j++)
                    if ((mask & (1 << j)) != 0)
                        subsets.Last().Add(nums[j]);
            }
            return subsets;
        }

        // 90
        // 1844. Replace All Digits with Characters
        public string ReplaceDigits(string s)
        {
            StringBuilder result = new StringBuilder();
            for (int i = 1; i < s.Length; i += 2)
            {
                result.Append(s[i - 1]);
                result.Append((char) (s[i - 1] + Int32.Parse(s[i].ToString())));
            }
            if (s.Length % 2 == 1) result.Append(s[s.Length - 1]);
            return result.ToString();
        }

        // 91
        // 1415. The k-th Lexicographical String of All Happy Strings of Length n
        public string GetHappyString(int n, int k)
        {
            int permutations = 1 << (n - 1);
            if (k > 3 * permutations) return "";
            int c = 'a' + (k - 1) / permutations;
            StringBuilder result = new StringBuilder();
            result.Append((char) c);
            while (permutations > 1)
            {
                k = (k - 1) % permutations + 1;
                permutations >>= 1;
                c = (k - 1) / permutations == 0 ? 'a' + (c == 'a' ? 1 : 0) : 'b' + (c != 'c' ? 1 : 0);
                result.Append((char) c);
            }
            return result.ToString();
        }

        // 92
        // 1021. Remove Outermost Parentheses
        public string RemoveOuterParentheses(string s)
        {
            StringBuilder result = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            int balance = 0;
            for (int i = 0; i < s.Length; i++)
            {
                if (s[i] == '(') balance++;
                else balance--;
                if (balance == 0)
                {
                    temp.Remove(0, 1);
                    result.Append(temp);
                    temp.Clear();
                }
                else temp.Append(s[i]);
            }
            return result.ToString();
        }

        // 93
        // 1310. XOR Queries of a Subarray
        public int[] XorQueries(int[] arr, int[][] queries)
        {
            int[] xor = new int[arr.Length];
            xor[0] = arr[0];
            for (int i = 1; i < xor.Length; i++)
                xor[i] = xor[i - 1] ^ arr[i];
            int[] result = new int[queries.Length];
            for (int i = 0; i < queries.Length; i++)
            {
                int l = queries[i][0];
                int r = queries[i][1];
                if (l == 0) result[i] = xor[r];
                else result[i] = xor[l - 1] ^ xor[r];
            }
            return result;
        }

        // 94
        // 832. Flipping an Image
        public int[][] FlipAndInvertImage(int[][] image)
        {
            int rows = image.Length;
            int cols = image[0].Length;
            int[][] matrix = new int[rows][];
            for (int i = 0; i < rows; i++)
                matrix[i] = new int[cols];
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++)
                {
                    if (image[i][cols - 1 - j] == 0) matrix[i][j] = 1;
                    else matrix[i][j] = 0;
                }
            return matrix;
        }

        // 95
        // 986. Interval List Intersections
        public int[][] IntervalIntersection(int[][] firstList, int[][] secondList)
        {
            IList<IList<int>> temp = new List<IList<int>>();
            int i = 0;
            int j = 0;
            while (i < firstList.Length && j < secondList.Length)
            {
                int[] points = new int[2];
                points[0] = Math.Max(firstList[i][0], secondList[j][0]);
                points[1] = Math.Min(firstList[i][1], secondList[j][1]);
                if (points[0] <= points[1]) temp.Add(points);
                if (firstList[i][1] < secondList[j][1]) i++;
                else j++;
            }
            int[][] result = new int[temp.Count][];
            for (i = 0; i < temp.Count; i++)
                result[i] = new int[2];
            for (i = 0; i < result.Length; i++)
            {
                result[i][0] = temp[i][0];
                result[i][1] = temp[i][1];
            }
            return result;
        }

        // 96
        // 1967. Number of Strings That Appear as Substrings in Word
        public int NumOfStrings(string[] patterns, string word)
        {
            int result = 0;
            for (int i = 0; i < patterns.Length; i++)
                if (word.Contains(patterns[i])) result++;
            return result;
        }

        // 97
        // 537. Complex Number Multiplication
        public string ComplexNumberMultiply(string num1, string num2)
        {
            StringBuilder result = new StringBuilder();
            String[] a = num1.Split("+");
            String[] b = num2.Split("+");
            int x1 = Int32.Parse(a[0]);
            int y1 = Int32.Parse(a[1].Replace("i", ""));
            int x2 = Int32.Parse(b[0]);
            int y2 = Int32.Parse(b[1].Replace("i", ""));
            return result.Append(x1 * x2 + (-1 * y1 * y2)).Append('+').Append(x1 * y2 + x2 * y1).Append('i').ToString();
        }

        // 98
        // 1572. Matrix Diagonal Sum
        public int DiagonalSum(int[][] mat)
        {
            int result = 0;
            int n = mat.Length;
            for (int i = 0; i < n; i++)
            {
                result += mat[i][i];
                result += mat[n - 1 - i][i];
            }
            if (n % 2 == 1) result -= mat[n / 2][n / 2];
            return result;
        }

        // 99
        // 1043. Partition Array for Maximum Sum
        public int MaxSumAfterPartitioning(int[] arr, int k)
        {
            int[] dp = new int[k];
            for (int i = 1; i <= arr.Length; i++)
            {
                int currentMax = 0;
                int best = 0;
                for (int j = 1; j <= k && i - j >= 0; j++)
                {
                    currentMax = Math.Max(currentMax, arr[i - j]);
                    best = Math.Max(best, dp[(i - j) % k] + currentMax * j);
                }
                dp[i % k] = best;
            }
            return dp[arr.Length % k];
        }

        // 100
        // 1323. Maximum 69 Number
        public int Maximum69Number(int num)
        {

            int index = ("" + num).IndexOf('6');
            if (index == -1) return num;
            return Int32.Parse(("" + num).Remove(index, 1).Insert(index, "9"));
        }

    }
}
