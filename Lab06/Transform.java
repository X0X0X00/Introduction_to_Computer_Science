/*
zhenhao zhang zzh133@u.rochester.edu 32277234
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Entry implements Comparable<Entry> {
    // the properties of the Entry class
    public String lastName;
    public String firstName;
    public String semester;
    public String course;

    public Entry(String line){
        // lastName, firstName, semester, course.
        // e.g. zhang, zhenhao, F22, 171
        String[] parts = line.split(",");
        // cut the input line and assign them to the instance variables
        this.lastName = parts[0];
        this.firstName = parts[1];
        this.semester = parts[2];
        this.course = parts[3];

    }

    public double translate(String semester){
        // translate the semester to a double number
        // e.g. F22 -> 22.5
        // e.g. S20 -> 20.0
        char season = semester.charAt(0);
        int year = Integer.parseInt(semester.substring(1));
        float result = 0;
        if (season == 'F'){
            // fall
            result = year + 0.5f;
        } else if (season == 'S'){
            // spring
            result = year + 0.0f;
        }
        return result;
    }

    @Override
    public int compareTo(Entry other) {
//        ascending order by semester, then course, then last name, then first name.
//        This means a.compareTo(b) should return a negative number when a should appear earlier than b.
        if (translate(semester) == translate(other.semester)){
            if(course.equals(other.course)) {
                if(lastName.equals(other.lastName)) {
                    // if semester, course, lastName are all the same, sort by ascending order by first name
                    return firstName.compareTo(other.firstName);
                }
                // if semester, course are the same, sort by ascending order by last name
                return lastName.compareTo(other.lastName);
            }
            // if semester is the same, sort by ascending order by course
            return course.compareTo(other.course);
        }
        else if (translate(semester) > translate(other.semester)){
            return 1;
        }
        else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return String.join(",", semester, course, lastName, firstName);
        // the same as the follows
//        return semester + "," + course + "," + lastName + "," + firstName;
    }
}


public class Transform{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        // the container to store all the entries
        List<Entry> Students = new ArrayList<>();
        while(s.hasNextLine()){
            String line = s.nextLine();
            if(line.isEmpty()){
                // read the input until the blank line, then break
                break;
            }
            else{
                // if not empty, add the entry to the container
                Students.add(new Entry(line));
            }
        }
        // read the query
        int query = s.nextInt();
        // sort the container
        Collections.sort(Students);
        for(Entry every_one : Students){
            // for each person, print the entry if the person's course number equals to the query
            if(every_one.course.equals(String.valueOf(query))){
                // print the entry using the toString() method
                System.out.println(every_one);
            }
        }
    }
}