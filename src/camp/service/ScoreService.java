package camp.service;

import camp.model.GradeGenerator;
import camp.model.Score;
import camp.model.Student;
import camp.model.StudentAverageGrade;
import camp.model.Subject;
import camp.model.SubjectAverageGrade;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreService {

    private static ScoreService instance;

    private final ScoreRepository scoreRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    private final GradeGenerator gradeGenerator;

    private ScoreService(ScoreRepository scoreRepository, StudentRepository studentRepository, SubjectRepository subjectRepository,
            GradeGenerator gradeGenerator) {
        this.scoreRepository = scoreRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.gradeGenerator = gradeGenerator;
    }

    public static ScoreService getInstance(ScoreRepository scoreRepository, StudentRepository studentRepository, SubjectRepository subjectRepository,
            GradeGenerator gradeGenerator) {
        if (Objects.isNull(instance)) {
            instance = new ScoreService(scoreRepository, studentRepository, subjectRepository, gradeGenerator);
        }
        return instance;
    }

    public void registerScore(String studentId, String subjectId, int round, int scoreValue) {
        if (studentRepository.doesNotExist(studentId)) {
            throw new IllegalArgumentException("존재하지 않는 수강생 ID 입니다.");
        }
        if (subjectRepository.doesNotExist(subjectId)) {
            throw new IllegalArgumentException("존재하지 않는 과목 ID 입니다.");
        }
        if (scoreRepository.existScore(studentId, subjectId, round)) {
            throw new IllegalArgumentException("과목의 회차 점수는 중복되어 등록될 수 없습니다.");
        }

        Subject subject = subjectRepository.findById(subjectId);
        String grade = gradeGenerator.generateGrade(subject.getSubjectType(), scoreValue);

        Score score = new Score(subjectId, studentId, round, scoreValue, grade);
        scoreRepository.save(score);
    }

    public Score updateScore(String studentId, String subjectId, int round, int scoreValue) {
        if (studentRepository.doesNotExist(studentId)) {
            throw new IllegalArgumentException("존재하지 않는 수강생 ID 입니다.");
        }
        if (subjectRepository.doesNotExist(subjectId)) {
            throw new IllegalArgumentException("존재하지 않는 과목 ID 입니다.");
        }
        if (scoreRepository.doesNotExist(studentId, subjectId, round)) {
            throw new IllegalArgumentException("존재하지 않는 회차입니다.");
        }

        Subject subject = subjectRepository.findById(subjectId);
        String grade = gradeGenerator.generateGrade(subject.getSubjectType(), scoreValue);

        return scoreRepository.updateScore(studentId, subjectId, round, scoreValue, grade);
    }

    public List<Score> getScoresByStudentIdAndSubjectId(String studentId, String subjectId) {
        if (studentRepository.doesNotExist(studentId)) {
            throw new IllegalArgumentException("존재하지 않는 수강생 ID 입니다.");
        }
        if (subjectRepository.doesNotExist(subjectId)) {
            throw new IllegalArgumentException("존재하지 않는 과목 ID 입니다.");
        }

        return scoreRepository.findByStudentIdAndSubjectId(studentId, subjectId);
    }

    public List<StudentAverageGrade> getAverageGradesByStatusAndSubjectType(String status, String subjectType) {
        List<Student> students = studentRepository.findAllByStatus(status);
        if (students.isEmpty()) {
            throw new IllegalArgumentException("일치하는 상태의 수강생이 없습니다.");
        }

        List<StudentAverageGrade> studentAverageGrades = new ArrayList<>();
        for (Student student : students) {
            int totalScoreValue = 0;
            int count = 0;
            List<Subject> subjects = subjectRepository.findAllBySubjectType(subjectType);
            for (Subject subject : subjects) {
                List<Score> scores = scoreRepository.findAllByStudentIdAndSubjectId(student.getStudentId(), subject.getSubjectId());

                for (Score score : scores) {
                    totalScoreValue += score.getValue();
                    count++;
                }
            }

            String grade = gradeGenerator.generateGrade(subjectType, totalScoreValue / count);
            StudentAverageGrade studentAverageGrade = new StudentAverageGrade(student.getStudentName(), grade);
            studentAverageGrades.add(studentAverageGrade);
        }

        return studentAverageGrades;
    }

    public List<SubjectAverageGrade> getSubjectAverageGradesByStudentId(String studentId) {
        Student student = studentRepository.findById(studentId);
        List<Subject> subjects = student.getSubjects();

        List<SubjectAverageGrade> subjectAverageGrades = new ArrayList<>();
        for (Subject subject : subjects) {
            List<Score> scores = scoreRepository.findAllByStudentIdAndSubjectId(studentId, subject.getSubjectId());
            if (!scores.isEmpty()) {
                int totalScoreValue = 0;
                int count = scores.size();
                for (Score score : scores) {
                    totalScoreValue += score.getScoreValue();
                }

                String grade = gradeGenerator.generateGrade(subject.getSubjectType(), totalScoreValue / count);
                SubjectAverageGrade subjectAverageGrade = new SubjectAverageGrade(subject.getSubjectName(), grade);
                subjectAverageGrades.add(subjectAverageGrade);
            }
        }

        if (subjectAverageGrades.isEmpty()) {
            throw new IllegalArgumentException("해당 수강생에 대한 점수 데이터가 없습니다.");
        }

        return subjectAverageGrades;
    }
}
