package org.example.ques1;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
class StudentUtil {

    private static final Logger logger=LoggerFactory.getLogger(StudentUtil.class);
    /**
     *
     * @param studentIdList
     * @param sGrades
     * @return
     */
    public static double[] calculateGPA(int[] studentIdList, char[][] sGrades) {
        double grades[]=new double[studentIdList.length];
        int i,j;
        double gpa;
        char ch;

        for(i=0;i<sGrades.length;i++){
            gpa=0;
            for(j=0;j<sGrades[0].length;j++) {
                ch = sGrades[i][j];
                if (ch != '\0') {
                    if (ch == 'A')
                        gpa += 4;
                    else if (ch == 'B')
                        gpa += 3;
                    else if (ch == 'C')
                        gpa += 2;
                    else
                        gpa += 1;
                }
            }
            gpa/=4;
            grades[i]=gpa;
        }

        return grades;
    }
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if(lower > higher || lower < 0 || higher < 0 || studentsGrades.length != studentIdList.length)
            return null;

        double[] gpa_avg = calculateGPA(studentIdList, studentsGrades);
        int count = 0;
        for(double val : gpa_avg){
            if(val >= lower && val <= higher) count++;
        }

        int[] listOfStudents = new int[count];
        int index = 0;
        for(int i = 0; i < gpa_avg.length; i++){
            if(gpa_avg[i] >= lower && gpa_avg[i] <= higher){
                listOfStudents[index] = studentIdList[i];
                index++;
            }
        }
        return listOfStudents;
    }

    public static void main(String[] args) {

        int []studentIdList={1001,1002};
        char [][]studentsGrades = {{'A','A','B','B'}, { 'A', 'A', 'C','A'}};

        double []grades=calculateGPA(studentIdList,studentsGrades);

        int []gpaList=getStudentsByGPA(3,4,studentIdList,studentsGrades);

        for(int i=0;i<gpaList.length;i++)
            logger.info("{}",gpaList[i]);

    }
}