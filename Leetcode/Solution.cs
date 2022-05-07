using System;
using System.Text;
using System.Collections.Generic;

namespace Leetcode
{
    public class Solution
    {

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

        // 2235. Add Two Integers
        public int Sum(int num1, int num2)
        {
            return num1 + num2;
        }

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

        // 1551. Minimum Operations to Make Array Equal
        public int MinOperations(int n)
        {
            if (n % 2 == 1) return (n - 1) * (n / 2 + 1) / 2;
            else return n * n / 4;
        }

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

        // 1920. Build Array from Permutation
        public int[] BuildArray(int[] nums)
        {
            int[] array = new int[nums.Length];
            for (int i = 0; i < nums.Length; i++)
                array[i] = nums[nums[i]];
            return array;
        }

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

        // 1480. Running Sum of 1d Array
        public int[] RunningSum(int[] nums)
        {
            int[] sums = new int[nums.Length];
            sums[0] = nums[0];
            for (int i = 1; i < nums.Length; i++)
                sums[i] = sums[i - 1] + nums[i];
            return sums;
        }

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

        // 1108. Defanging an IP Address
        public string DefangIPaddr(string address)
        {
            return address.Replace(".", "[.]");
        }

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

    }
}
