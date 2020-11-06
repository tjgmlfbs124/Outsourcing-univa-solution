package com.tathink.univa.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.controller.ProblemForm;
import com.tathink.univa.controller.SolutionForm;
import com.tathink.univa.domain.Problem;
import com.tathink.univa.domain.Solution;
import com.tathink.univa.domain.SolutionState;
import com.tathink.univa.repository.SolutionRepository;
import com.tathink.univa.utils.FileUtil;
import com.tathink.univa.utils.StringUtil;

@Transactional
public class SolutionService {
	
	private final SolutionRepository qRepository;
	
	public SolutionService(SolutionRepository qRepository) {
		this.qRepository = qRepository;
	}
	/**
	 * 질문 등록
	 */
	public int apply(SolutionForm form) {
		
		Solution solution = new Solution();
		solution.setTitle(form.getTitle());
		solution.setNickname(form.getNickname());
		solution.setContent(form.getContent());
		solution.setPassword(form.getPassword());
		solution.setLimit_date(form.getLimit_date());
		for (ProblemForm mForm : form.getProblems()) {
			System.out.println(mForm.getNumber());
			Problem problem = new Problem();
			problem.setQuestion_id(solution);
			problem.setNumber(mForm.getNumber());
			problem.setText(mForm.getText());
			if(mForm.getFile() == null) {
				System.out.println("file is null");
			}
			if(mForm.getFile() != null) {
				String randomStr = StringUtil.RandomString(20)+"/";
				String randomPath = "uploads/imgs/"+randomStr;
				String imagePath = randomPath+"img"+StringUtil.getExtension(mForm.getFile().getOriginalFilename()).get();
				try {
					File mFile = new File(randomPath);
					mFile.mkdirs();
				} catch (Exception e) {
					e.getStackTrace();
				}
				
				FileUtil.FileWrite(mForm.getFile(), imagePath);
				problem.setImage_url(imagePath);
			}
			
			solution.addProblem(problem);
		}
		
		return qRepository.save(solution).getId();
	}
	
	/* id로 찾기 */
	public Optional<Solution> findOne(int id) {
		Optional<Solution> question = qRepository.findById(id);
		return question;
	}
	
	/* 모든 질문 찾기 */
	public List<Solution> findAllQuestions() {
		return qRepository.findAll();
	}
	
	/* 모든 질문 갯수 찾기 */
	public int length() {
		return findAllQuestions().size();
	}
	
	/* 최근 ~개 질문 찾기 */
	public List<Solution> findRecently(int amount) {
		return qRepository.findRecently(amount);
	}
	
	/* 한계 및 상태 설정 질문 찾기 */
	public List<Solution> findList(int lowLimit, int highLimit, int state) {
		List<Solution> solutions;
		if(state>0) {
			SolutionState qstate = new SolutionState();
			qstate.setId(state);
			solutions = qRepository.findLimitAndState(lowLimit, highLimit, qstate);
		} else {			
			solutions = qRepository.findLimit(lowLimit, highLimit);
		}
		
		return solutions;
	}
	
	/* 해당 질문의 문제받기 */
	public List<Problem> findProblem(Solution sol) {
		List<Problem> problems = qRepository.findByIdForProblem(sol);
		return problems;
	}
}
