
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedMap;

d)private List<Internship>internships;

public Iterator<Internship>listBySubject(String subject)throws SubjectDoesNotExistException{
// key: Titulo, value: Internship
SortedMap<String,Internship>subj=new TreeMap<String,Internship>();

for(Internship i:internships){if(i.coversSubject(subject)){subj.put(i.getTitle(),i);}}if(subj.size()==0){throw new SubjectDoesNotExistException();}

return subj.values().iterator();

}

e)public Iterator<Internship>listSummerIntershipsClass()throws NoSummerInternshipsException{

List<Intership>temp=new ArrayList<Intership>();

for(Internship i:internships){if(i instanceof SummerIntership){temp.add(i);}}if(temp.isEmpty()){throw new NoSummerInternshipsException();}

return temp.iterator();

}

II

a)
// key: Titulo , Value: Intership
private Map<String,Internship>internships;
// key: subject, Value: List ordered by titles of Internships
private Map<String,SortedSet<Intership>>internshipsBySubject;

// number of placements still to fill max capacity
private int totalAvailablePlacements;

private SortedSet<Internship>availableInterneships;

b)

public NationalInternshipsClass() {

insternships=new HashMap<String,Internship>();internshipsBySubject=new HashMap<String,SortedSet<Intership>>();totalAvailablePlacements=0;availableInterneships=new TreeSet<Intership>(new AvailabilityComparator());

}c)

public void addWeekInternsip(String title,int capacity,String institution,String subject)throws InternshipAlreadyExistsException{

if(insternships.get(title)!=null){throw new InternshipAlreadyExistsException();}

Intership intership=new WeekIntershipClass(title,capacity,institution,subject);

internships.put(title,intership);

SortedSet<Intership>set=internshipsBySubject.get(subject);if(set==null){set=new TreeSet<Intership>(new TitleComparator());internshipsBySubject.put(subject,set);}set.add(intership);

availableInterneships.add(intership);

totalAvailablePlacements+=capacity;

}

d)

public void registerStudent(String title,String email)throws InternshipDoesNotExistException,StudentAlreadyExistsException,InternshipFullException{

if(insternships.containsKey(title)){throw new InternshipDoesNotExistException();}

Internship intership=internships.get(title);intership.register(email);

totalAvailablePlacements--;

}

e)public int totalAvailablePlacements(){return totalAvailablePlacements;}

f)private SortedSet<Internship>availableInterneships;

public Iterator<Internship>availableInternships()throws NoAvailableInternshipsException{

List<Intership>aux=new ArrayList<Intership>(availableInterneships.size());

for(Internship i:availableInterneships){if(i.availabity()>0){aux.add(i);}}if(aux.isEmpty()){throw new NoAvailableInternshipsException();}

return aux.iterator();

}

III

public static int containsAllStudents(Internship i1,Internship i2){

Iterator<String>it1=i1.listStudents();Iterator<String>it2=i2.listStudents();int counter=0;int found=0;int index=0;int number=0;

while(it2.hasNext()){number=0;counter++;String name2=it2.next();

while(it1.hasNext()){number++;String name1=it1.next();

if(name2.equals(name1)){found++;if(conter==0||number<index){index=number;}}

}}

if(counter==found){return index;}return 0;

}
