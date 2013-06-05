import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class CourseXMLParser {
 private Course[] courses;
 
 public CourseXMLParser(Course[] myCourses)	{
	 this.courses=myCourses;
 } 
 
 public Course[] getCourses()	{
	 return courses;
 }
 
 private Document[] getXMLforEachCourse() throws DocumentException	{
	 Document[] xmlDocs= new Document[courses.length];
	 for(int i=0;i<xmlDocs.length;i++)	{
	        SAXReader reader = new SAXReader();
	        Document document=null;
			try {
				document = reader.read(new URL(courses[i].getXMLUrl()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        xmlDocs[i]=document;
	 }
	 return xmlDocs;
 }
 
 public void updateCourseSectionInfo() {
	 Document[] docs=null;
	try {
		docs = this.getXMLforEachCourse();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 extractDatafromDocs(docs);
 }
 
 /*
int crn //
String sectionNumber
String sectionText
String sectionNotes
String sectionType
String instructor
String availability
  */
 
 private void extractDatafromDocs(Document[] docs)	{
	 if (docs==null)	{
		 System.out.print("Hey its null yo");
		 return;
	 }
	 for(int docIndex=0;docIndex<docs.length;docIndex++)	{
		 Element root= docs[docIndex].getRootElement();
		 Element sectionsElement=root.element("detailedSections");
		 int numSections=sectionsElement.nodeCount();
		 Section[] sections= new Section[numSections];
		 int index=0;
		 for ( Iterator i = sectionsElement.elementIterator( "detailedSection" ); i.hasNext(); ) {
			 	int crn;
			 	String sectionNumber,sectionText,sectionNotes,availability,sectionType,instructor="";
			 	Element section = (Element) i.next();
	            crn=Integer.parseInt(section.attributeValue("id"));
	            sectionNumber=section.elementText("sectionNumber");
	            sectionText=section.elementText("sectionText");
	            sectionNotes=section.elementText("sectionNotes");
	            availability=section.elementText("enrollmentStatus");
	            Element meeting=section.element("meetings").element("meeting");
	            sectionType=meeting.elementText("type");
	            for(Iterator<Element> a=meeting.element("instructors").elementIterator();a.hasNext();)	{
	            	Element inst=a.next();
	            	String first=", "+inst.attributeValue("first");
	            	String last=inst.attributeValue("last");
	            	instructor+=last+first+"; \n";
	            }
	            if(instructor.length()>0) instructor=instructor.substring(0, instructor.length()-1);
	            //(int crnNum,String num,String text,String notes,String type,String instr,String avail)
	            sections[index]=new Section(crn,sectionNumber,sectionText,sectionNotes,sectionType,instructor,availability);
	            index++;
	        }
		courses[docIndex].setSections(sections); 
	 }
 }
 
 
	
	
}
