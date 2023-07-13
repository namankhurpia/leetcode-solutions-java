//https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1

class meetingcomparator implements Comparator<meeting>
{
    @Override
    public int compare(meeting m1, meeting m2)
    {
        if(m1.end<m2.end)
            return -1;
        else if (m1.end>m2.end)
            return 1;
        else if (m1.pos<m2.pos)
            return -1;
        else 
            return 1;
    }
}

class meeting{
    int start;
    int end ;
    int pos;

    meeting(int start, int end, int pos)
    {
        this.start = start;
        this.end = end ;
        this.pos = pos;
    }
}
class Solution 
{
    
    //Function to find the maximum number of meetings that can
    //be performed in a meeting room.
    public static int maxMeetings(int start[], int end[], int n)
    {
        // add your code here
        ArrayList<meeting> arr = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            arr.add(new meeting(start[i], end[i], i+1));
        }
        meetingcomparator mc = new meetingcomparator();
        Collections.sort(arr,mc);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr.get(0).pos);
        int limit = arr.get(0).end;
        
        for(int i=0;i<start.length;i++)
        {
            if(arr.get(i).start>limit)
            {
                list.add(arr.get(i).pos);
                limit = arr.get(i).end;
            }
        }
        
        return list.size();
        
    }
}