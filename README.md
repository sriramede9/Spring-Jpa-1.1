# Spring-Jpa-1.1
# Going through Documentation of Spring-Jpa 1.1

## Following Examples as listed in https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#query-by-example

## Used PagingAndSorting interface and applied to sort Person entity properties by firstname and by lastname

### Important Code Snippets
```
	@GetMapping("/Persons/Sortbylastname")
	public Iterable<Person> getSortedbyLastName() {

		// Sort sort;
		Iterable<Person> findAll = pagingAndSortingPerson.findAll(Sort.by(Direction.DESC, "lastname"));

		return findAll;

	}
	@GetMapping("/Persons/Sortbyfirstname")
	public Iterable<Person> getSortedbyFirstName() {
		
		// Sort sort;
		Iterable<Person> findAll = pagingAndSortingPerson.findAll(Sort.by(Direction.DESC, "firstname"));
		 String orders="firstname";
		Iterable<Person> findAll2 = pagingAndSortingPerson.findAll(Sort.by(orders));
		
		return findAll2; 
		
	}
```
### Using Customized methods in <em>JPARepository<em> findbyFirstName and findbyLastname
	
	```
	public interface PersonJpaRepository extends JpaRepository<Person, String> {

	Person findByLastname(String lastname);
	Person findByFirstname(String firstname);
}
	
	
		@GetMapping("/Persons/lastname/{lastname}")
	public Person getbyLasttName(@PathVariable String lastname) {
		
		Person findByLastname = personJpaRepository.findByLastname(lastname);
		
		return findByLastname; 
	}
	
	@GetMapping("/Persons/firstname/{firstname}")
	public Person getbyFirstName(@PathVariable String firstname) {
		
		Person findByFirstname = personJpaRepository.findByFirstname(firstname);
		
		return findByFirstname; 
	}
	```
