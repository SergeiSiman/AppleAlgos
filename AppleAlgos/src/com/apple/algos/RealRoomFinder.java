package com.apple.algos;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/*
https://adventofcode.com/2016/day/4
*/
public class RealRoomFinder
{
    private static Pattern regExpr_ = Pattern.compile("^(([a-z]+-)+)(\\d+)(\\[[a-z]+\\])$");

    // Returns roomID or 0 for "decoy"
    private static int getRealRoomID(String room)
    {
        // Check matching format through regular expression
        Matcher m = regExpr_.matcher(room);
        if (!m.matches())
            return 0;

        String strRoomName = m.group(1);
        strRoomName = strRoomName.replaceAll("-","");
        String strSectorID = m.group(3);
        String strCheckSum = m.group(4);
        strCheckSum = strCheckSum.substring(1, strCheckSum.length()-1);

        char[] chars = strRoomName.toCharArray();
        // Hash table to store quantity of each character
        Map<Character, Integer> letterMap = new HashMap<Character, Integer>();
        for (char ch:chars)
            letterMap.put(ch,letterMap.getOrDefault(ch, 0)+1);

        // Sort map by quantities
        Map<Character, Integer> sortedMap = letterMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
                .collect( toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));


        // Build our own version of checksum
        String myCheckSum =  sortedMap.keySet().stream().map(String::valueOf).collect(Collectors.joining());
        myCheckSum = myCheckSum.substring(0,5);
        if (myCheckSum.equals(strCheckSum))
            return Integer.parseInt(strSectorID);

        return 0;
    }
    public static int getRealRoomIDsSum(String[] rooms)
    {
        int retVal = 0;
        for (String room: rooms)
            retVal += getRealRoomID(room);

        return retVal;
    }
    public static void main(String[] args)
    {
        int roomID = getRealRoomID("not-a-real-room-404[oarel]");
        roomID = getRealRoomID("a-b-c-d-e-f-g-h-987[abcde]");
        roomID = getRealRoomID("aaaaa-bbb-z-y-x-123[abxyz]");
        roomID = getRealRoomID("totally-real-room-200[decoy]");

        if (args.length != 1)
        {
            System.out.println("Usage: RealRoomFinder [fileName]");
            return;
        }
        File inputFile = new File(args[0]);
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new FileReader(inputFile));
            String str;
            List<String> list = new ArrayList<String>();
            while((str = in.readLine()) != null){
                list.add(str);
            }
            String[] rooms = list.toArray(new String[0]);

            int retVal = RealRoomFinder.getRealRoomIDsSum(rooms);
            System.out.println("Sum of real rooms IDs is: " + retVal);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("File does not exist: " + args[0]);
            return;
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("File is corrupt: " + args[0]);
            return;
        }
    }

}
