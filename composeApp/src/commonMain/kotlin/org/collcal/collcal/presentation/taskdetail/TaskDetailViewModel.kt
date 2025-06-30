package org.collcal.collcal.presentation.taskdetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.collcal.collcal.presentation.taskdetail.model.TaskInfo
import org.collcal.collcal.presentation.tasks.model.Task

class TaskDetailViewModel : ViewModel() {

    private val _taskInfo = MutableStateFlow<TaskInfo?>(null)
    val taskInfo: StateFlow<TaskInfo?> = _taskInfo

    fun getTaskInfo(task: Task?) {
        task?.let {
            _taskInfo.value = when (it.title) {
                "학부연구생" -> TaskInfo(
                    listOf(
                        "교수님의 연구실에서 연구보조 업무 수행",
                        "데이터 수집, 실험 설계, 논문 작성 일부 보조 등의 역할을 담당",
                        "연구실 세미나 참석 및 논문 발표 준비에 참여"
                    ),
                    "3학년 1학기부터 지원서 제출 추천",
                    listOf(
                        "공지사항 링크 : https://eng.khu.ac.kr/eng_kor/user/bbs/BMSR00040/list.do?menuNo=17100020&boardType=&pageIndex=1&searchCategory=&searchCondition=&searchKeyword=연구실&userDisplayCount=10",
                        "교수진 / 랩실 링크 : https://ie.khu.ac.kr/ie/user/professor/list.do?menuNo=17400014"
                    )
                )

                "전공연수" -> TaskInfo(
                    listOf("전공 관련 해외 교육기관이나 대학에서 단기 수업/워크숍/현장견학 등에 참여. (해외 계절학기)"),
                    "2학년 하계방학부터 꾸준하게 넣어보는 걸 추천",
                    listOf(
                        "공지사항 링크 : https://eng.khu.ac.kr/eng_kor/user/bbs/BMSR00040/list.do?menuNo=17100020&boardType=&pageIndex=1&searchCategory=&searchCondition=&searchKeyword=전공연수&userDisplayCount=10"
                    )
                )

                "현장실습" -> TaskInfo(
                    listOf(
                        "산업체 또는 연구기관에 파견되어 실무를 직접 경험하는 프로그램",
                        "기업 프로젝트 참여, 공정관리/데이터 분석 등 실무 스킬 습득 기회 제공",
                        "학점인정 가능"
                    ),
                    "3학년 1학기 이후 휴학학기",
                    listOf("공지사항 링크 : https://eng.khu.ac.kr/eng_kor/user/bbs/BMSR00040/list.do?menuNo=17100020&boardType=&pageIndex=1&searchCategory=&searchCondition=&searchKeyword=현장실습&userDisplayCount=10")
                )

                "외국인도우미" -> TaskInfo(
                    listOf("외국인도우미"),
                    "1학년, 2학년",
                    listOf("공지사항 링크 : https://iie.khu.ac.kr/bbs/board.php?bo_table=notice_temp&sca=&sop=and&beforeYear=&helfer=&sfl=wr_subject&stx=한국어+도우미")
                )

                "인턴" -> TaskInfo(
                    listOf(
                        "기업 또는 연구소에서 일정 기간 정해진 직무를 수행하며 실무 경험을 쌓는 활동",
                        "데이터 분석, 공정 개선, 품질관리, 마케팅 기획 등 다양한 직무에 참여 가능"
                    ),
                    "3학년 마친 뒤 휴학 기간",
                    listOf("사람인, 링커리어에서 확인 가능")
                )

                else -> _taskInfo.value
            }
        }
    }
}









