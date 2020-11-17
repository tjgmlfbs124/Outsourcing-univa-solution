package com.tathink.univa.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.controller.form.AnswerForm;
import com.tathink.univa.controller.form.AnswerSubForm;
import com.tathink.univa.controller.form.ChatJsonForm;
import com.tathink.univa.controller.form.ProblemForm;
import com.tathink.univa.controller.form.ReviewForm;
import com.tathink.univa.controller.form.SolutionForm;
import com.tathink.univa.domain.Answer;
import com.tathink.univa.domain.AnswerSub;
import com.tathink.univa.domain.Problem;
import com.tathink.univa.domain.Solution;
import com.tathink.univa.domain.SolutionChat;
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
	
	/**질문 등록*/
	public int apply(SolutionForm form) {
		
		Solution solution = new Solution();
		solution.setTitle(form.getTitle());
		solution.setNickname(form.getNickname());
		solution.setContent(form.getContent());
		solution.setPassword(form.getPassword());
		solution.setLimit_date(form.getLimit_date());
		for (ProblemForm mForm : form.getProblems()) {
			Problem problem = new Problem();
			problem.setQuestion_id(solution);
			problem.setNumber(mForm.getNumber());
			problem.setText(mForm.getText());
			if(mForm.getFile() == null) {
				//System.out.println("file is null");
			}
			if(mForm.getFile() != null) {
				String dirPath = "uploads/imgs/";
				String randomStr = StringUtil.RandomString(20)+"/";
				String imageUrl = randomStr+"img"+StringUtil.getExtension(mForm.getFile().getOriginalFilename()).get();
				String savePath = dirPath+imageUrl;
				try {
					File mFile = new File(dirPath+randomStr);
					mFile.mkdirs();
				} catch (Exception e) {
					e.getStackTrace();
				}
				
				FileUtil.FileWrite(mForm.getFile(), savePath);
				problem.setImage_url(imageUrl);
			}
			
			solution.addProblem(problem);
		}
		
		return qRepository.save(solution).getId();
	}
	
	/** 답변 업로드 */
	public int answerApply(AnswerForm form) {
		Answer answer = new Answer();
		answer.setQuestion( qRepository.findById(form.getSolution_id()).get());
		answer.setContent(form.getContent());
		
		for(AnswerSubForm mForm : form.getAnswerSub()) {
			AnswerSub answerSub = new AnswerSub();
			answerSub.setAnswer(answer);
			answerSub.setNumber(mForm.getNumber());
			answerSub.setText(mForm.getText());
			
			//answerSub.setImage_ur(image_url);
			if(mForm.getFile() != null) {
				String dirPath = "uploads/imgs/";
				String randomStr = StringUtil.RandomString(20)+"/";
				String imageUrl = randomStr+"img"+StringUtil.getExtension(mForm.getFile().getOriginalFilename()).get();
				String savePath = dirPath+imageUrl;
				try {
					File mFile = new File(dirPath+randomStr);
					mFile.mkdirs();
				} catch (Exception e) {
					e.getStackTrace();
				}
				FileUtil.FileWrite(mForm.getFile(), savePath);
				answerSub.setImage_ur(imageUrl);
			}
			answer.addAnsser_sub(answerSub);
		}
		updateState(answer.getQuestion(), qRepository.findStateById(4).get());
		return qRepository.save(answer).getId();
	}
	
	/** 상태 업로드 */
	public void updateState(Solution solution, SolutionState state) {
		solution.setState(state);
		qRepository.save(solution);
	}
	
	/** 리뷰 등록 */
	public int reviewApply(ReviewForm form) {
		System.out.println(form.getSolution_id());
		Solution solution = qRepository.findById(form.getSolution_id()).get();
		solution.setReview(form.getText());
		solution.setScore(form.getScore());
		solution.setState(qRepository.findStateById(5).get());
		qRepository.save(solution);
		
		return solution.getId();
	}
	
	/** id로 찾기 */
	public Optional<Solution> findOne(int id) {
		Optional<Solution> question = qRepository.findById(id);
		return question;
	}
	
	/** 모든 질문 찾기 */
	public List<Solution> findAllQuestions() {
		return qRepository.findAll();
	}
	
	/** 모든 질문 갯수 찾기 */
	public int length() {
		return findAllQuestions().size();
	}
	
	/** 최근 ~개 질문 찾기 */
	public List<Solution> findRecently(int amount) {
		return qRepository.findRecently(amount);
	}
	
	/** 한계 및 상태 설정 질문 찾기 */
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
	
	/** 해당 질문의 문제받기 */
	public List<Problem> findProblem(Solution sol) {
		List<Problem> problems = qRepository.findByIdForProblem(sol);
		return problems;
	}
	
	/** 질문 로그인 */
	public Boolean loginSolutionUser(int idx, String password) {
		Solution solution = qRepository.findById(idx).get();
		if(solution.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	/* 채팅 등록 */
	public Solution SolutionChatSave(ChatJsonForm form) {
		Solution solution = qRepository.findById(form.getSolution_id()).get();
		SolutionChat chat = new SolutionChat();
		chat.setSolution(solution);
		chat.setWriter(form.getWriter());
		chat.setContent(form.getContent());
		if(form.getFile() != null) {
			String dirPath = "uploads/imgs/";
			String randomStr = StringUtil.RandomString(20)+"/";
			String imageUrl = randomStr+"img"+StringUtil.getExtension(form.getFile().getOriginalFilename()).get();
			String savePath = dirPath+imageUrl;
			try {
				File mFile = new File(dirPath+randomStr);
				mFile.mkdirs();
			} catch (Exception e) {
				e.getStackTrace();
			}
			FileUtil.FileWrite(form.getFile(), savePath);
			chat.setImage_url(imageUrl);
		}
		
		solution.addChat(chat);
		qRepository.save(solution);
		return solution;
	}
	
	public List<ChatJsonForm> SolutionChatList(int id) {
		List<ChatJsonForm> chatForms = new ArrayList<ChatJsonForm>();
		for( SolutionChat chat : this.findOne(id).get().getChats()) {
			ChatJsonForm formChat = new ChatJsonForm();
			formChat.setContent(chat.getContent());
			//chatForm.add()
		}
		return chatForms;
	}
	
}
