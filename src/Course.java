
public class Course {
private int courseNumber;
private String courseDepartment;
private String semester;
private Section[] sections;


public static String FA2013= "2013/fall";
public static String SP2014="2014/spring";
public static String Fall="/fall";
public static String Spring="/spring";
public static String Summer="/summer";
public static String[][] coursesDeptKVMap={{"AAS","Asian American Studies"},{"ABE","Agricultural and Biological Engineering"},{"ACCY","Accountancy"},{"ACE","Agricultural and Consumer Economics"},{"ACES","Agricultural, Consumer and Environmental Sciences"},{"ADV","Advertising"},{"AE","Aerospace Engineering"},{"AFAS","Air Force Aerospace Studies"},{"AFRO","African American Studies"},{"AFST","African Studies"},{"AGCM","Agricultural Communications"},{"AGED","Agricultural Education"},{"AHS","Applied Health Sciences Courses"},{"AIS","American Indian Studies"},{"ANSC","Animal Sciences"},{"ANTH","Anthropology"},{"ARAB","Arabic"},{"ARCH","Architecture"},{"ART","Art"},{"ARTD","Art--Design"},{"ARTE","Art--Education"},{"ARTF","Art--Foundation"},{"ARTH","Art--History"},{"ARTS","Art--Studio"},{"ASST","Asian Studies"},{"ASTR","Astronomy"},{"ATMS","Atmospheric Sciences"},{"AVI","Aviation"},{"BADM","Business Administration"},{"BASQ","Basque"},{"BIOC","Biochemistry"},{"BIOE","Bioengineering"},{"BIOL","Biology"},{"BIOP","Biophysics"},{"BMNA","Bamana"},{"BTW","Business and Technical Writing"},{"BULG","Bulgarian"},{"BUS","Business"},{"CAS","Center for Advanced Study"},{"CATL","Catalan"},{"CB","Comparative Biosciences"},{"CDB","Cell and Developmental Biology"},{"CEE","Civil and Environmental Engineering"},{"CHBE","Chemical and Biomolecular Engineering"},{"CHEM","Chemistry"},{"CHIN","Chinese"},{"CHLH","Community Health"},{"CHP","Campus Honors Program Courses"},{"CI","Curriculum and Instruction"},{"CIC","Committee on Inst Cooperation"},{"CLCV","Classical Civilization"},{"CMN","Communication"},{"CPSC","Crop Sciences"},{"CS","Computer Science"},{"CSE","Computational Science and Engineering"},{"CW","Creative Writing"},{"CWL","Comparative and World Literature"},{"CZCH","Czech"},{"DANC","Dance"},{"EALC","East Asian Language and Culture"},{"ECE","Electrical and Computer Engineering"},{"ECON","Economics"},{"EDPR","Educational Practice"},{"EDUC","Education"},{"EIL","English as an International Language"},{"ENG","Engineering"},{"ENGH","Engineering Honors"},{"ENGL","English"},{"ENSU","Environmental Sustainability"},{"ENT","Entomology"},{"ENVS","Environmental Studies"},{"EOL","Educational Organization and Leadership"},{"EPS","Educational Policy Studies"},{"EPSY","Educational Psychology"},{"ESE","Earth, Society, and Environment"},{"ESL","English as a Second Language"},{"EURO","European Union Studies"},{"FAA","Fine and Applied Arts"},{"FIN","Finance"},{"FR","French"},{"FSHN","Food Science and Human Nutrition"},{"GC","Graduate College"},{"GE","General Engineering"},{"GEOG","Geography"},{"GEOL","Geology"},{"GER","German"},{"GLBL","Global Studies"},{"GMC","Germanic"},{"GRK","Greek"},{"GRKM","Modern Greek"},{"GS","General Studies"},{"GWS","Gender and Women's Studies"},{"HCD","Human and Community Development"},{"HDES","Human Dimensions of Environmental Systems"},{"HDFS","Human Development and Family Studies"},{"HEBR","Hebrew, Modern and Classical"},{"HIST","History"},{"HNDI","Hindi"},{"HORT","Horticulture"},{"HRE","Human Resource Education"},{"HUM","Humanities Courses"},{"IB","Integrative Biology"},{"IE","Industrial Engineering"},{"IHLT","i-Health Program"},{"INFO","Informatics"},{"ITAL","Italian"},{"JAPN","Japanese"},{"JOUR","Journalism"},{"JS","Jewish Studies"},{"KIN","Kinesiology"},{"KOR","Korean"},{"LA","Landscape Architecture"},{"LAS","Liberal Arts and Sciences"},{"LAST","Latin American and Caribbean Studies"},{"LAT","Latin"},{"LAW","Law"},{"LER","Labor and Employment Relations"},{"LGLA","Lingala"},{"LING","Linguistics"},{"LIS","Library and Information Science"},{"LLS","Latina/Latino Studies"},{"MACS","Media and Cinema Studies"},{"MATH","Mathematics"},{"MBA","Regular MBA Program Administration"},{"MCB","Molecular and Cell Biology"},{"MDIA","Media"},{"MDVL","Medieval Studies"},{"ME","Mechanical Engineering"},{"MICR","Microbiology"},{"MILS","Military Science"},{"MIP","Molecular and Integrative Physiology"},{"MSE","Materials Science and Engineering"},{"MSP","Medical Scholars Program"},{"MUS","Music"},{"MUSE","Museum Studies"},{"NEUR","Neuroscience"},{"NPRE","Nuclear, Plasma, and Radiological Engineering"},{"NRES","Natural Resources and Environmental Sciences"},{"NS","Naval Science"},{"NUTR","Nutritional Sciences"},{"PATH","Pathobiology"},{"PBIO","Plant Biology"},{"PERS","Persian"},{"PHIL","Philosophy"},{"PHYS","Physics"},{"PLPA","Plant Pathology"},{"POL","Polish"},{"PORT","Portuguese"},{"PS","Political Science"},{"PSM","Professional Science Master's Program"},{"PSYC","Psychology"},{"REES","Russian, East European and Eurasian Studies"},{"REHB","Rehabilitation Counseling"},{"RHET","Rhetoric and Composition"},{"RLST","Religious Studies"},{"RMLG","Romance Linguistics"},{"RSOC","Rural Sociology"},{"RST","Recreation, Sport, and Tourism"},{"RUSS","Russian"},{"SAME","South Asian and Middle Eastern Studies"},{"SCAN","Scandinavian"},{"SCR","Serbo-Croatian"},{"SHS","Speech and Hearing Science"},{"SLAV","Slavic"},{"SLS","Second Language Studies"},{"SNSK","Sanskrit"},{"SOC","Sociology"},{"SOCW","Social Work"},{"SPAN","Spanish"},{"SPED","Special Education"},{"STAT","Statistics"},{"SWAH","Swahili"},{"TAM","Theoretical and Applied Mechanics"},{"TE","Technology Entrepreneurship"},{"THEA","Theatre"},{"TMGT","Technology and Management Courses"},{"TRST","Translation Studies"},{"TSM","Technical Systems Management"},{"TURK","Turkish"},{"UKR","Ukrainian"},{"UP","Urban and Regional Planning"},{"VCM","Veterinary Clinical Medicine"},{"VM","Veterinary Medicine Courses"},{"WLOF","Wolof"},{"WRIT","Writing Studies"},{"YDSH","Yiddish"},{"ZULU","Zulu"}};

public Course(String department, int courseNum, String sem)	{ //Use if sem is in form of FA2013, SP2013 etc
	this.courseNumber=courseNum;
	this.courseDepartment= findDept(department,0,coursesDeptKVMap.length-1);
	String abbrev=sem.substring(0, 2);
	this.semester=sem.substring(2)+(abbrev.equals("FA")?Fall:(abbrev.equals("SP")?Spring:Summer));
}

public Course(String dept,int courseNum,String fallORspring, int year)	{
	this.courseNumber=courseNum;
	this.courseDepartment=findDept(dept,0,coursesDeptKVMap.length-1);
	this.semester=year+fallORspring;
}

public int getCourseNumber()	{
	return courseNumber;
}

public void setCourseNumber(int num)	{
	courseNumber=num;
}

public String getCourseDepartment() {
	return courseDepartment;
}

public void setCourseDepartment(String courseDepartment) {
	this.courseDepartment = courseDepartment;
}

public String getSemester() {
	return semester;
}

public void setSemester(String semester) {
	this.semester = semester;
}


public String getXMLUrl()	{
	return "http://courses.illinois.edu/cisapp/explorer/schedule/"+this.semester+"/"+this.courseDepartment+"/"+this.courseNumber+".xml?mode=detail";
}

private static String findDept(String abbrv,int lo,int hi)	{
	if(lo>=hi) return coursesDeptKVMap[lo][0];
	int mid=(lo+hi)/2;
	if(coursesDeptKVMap[mid][0].compareToIgnoreCase(abbrv)>0) return findDept(abbrv,lo,mid-1);
	return findDept(abbrv,mid+1,hi);
}

public Section[] getSections() {
	return sections;
}

public void setSections(Section[] sections) {
	this.sections = sections;
}

public String SummarayString()	{
	StringBuffer courseSumm=new StringBuffer("");
	courseSumm.append(courseDepartment+" "+courseNumber+" for "+convertSemesterURLtoReading(semester)+"\n");
	for(int i=0;i<sections.length;i++)	{
		courseSumm.append("\n Section "+(1+i)+sections[i].toString());
	}
	return courseSumm.toString();
}

public static String convertSemesterURLtoReading(String sem)	{
	String[] arr=sem.split("/");
	return arr[1].toUpperCase()+" "+arr[0];
}

}
