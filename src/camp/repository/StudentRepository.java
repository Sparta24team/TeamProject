package camp.repository;

import camp.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentRepository: 학생 데이터를 저장하고 관리
 *
 * 학생 데이터를 저장하는 메서드와 학생 데이터를 조회하는 메서드를 제공
 *
 * 주요 기능:
 * - 새로운 학생 저장
 * - 모든 학생 데이터 조회
 * - 학생 ID로 학생 조회
 * - 학생 삭제
 * - 학생 ID 시퀀스 생성
 */

public class StudentRepository {
    private static List<Student> studentStore = new ArrayList<>(); //// 학생 데이터를 저장하는 리스트
    private static int studentIndex = 0; // 학생 ID 시퀀스를 생성하기 위한 인덱스
    private static final String INDEX_TYPE_STUDENT = "ST";

    /**
     * 학생 ID 시퀀스 생성 [return]생성된 학생 ID
     */
    public static String sequence() {
        studentIndex++;
        return INDEX_TYPE_STUDENT + studentIndex;
    }

    /**
     * 새로운 학생을 저장합 [param] student 저장할 학생 객체
     */
    public void addStudent(Student student) {
        studentStore.add(student);
    }
    /**
     * 모든 학생 데이터를 반환 [return] 모든 학생 데이터가 포함된 리스트
     */
    public List<Student> getAllStudents() {
        return studentStore;
    }

    /**
     * 학생 ID로 학생을 조회
     * [param] studentId 조회할 학생 ID
     * [return] 조회된 학생 객체, 존재하지 않으면 null
     */
    public Student getStudentById(String studentId) {
        return studentStore.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    /**
     * 학생 ID로 학생을 삭제합니다.
     * [param] studentId 삭제할 학생 ID
     */
    public void deleteStudent(String studentId) {
        Student student = getStudentById(studentId);
        if (student != null) {
            studentStore.remove(student);
        }
    }
}
