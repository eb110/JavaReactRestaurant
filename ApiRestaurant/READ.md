Logic steps #########################################
main folder
add user service
compose mysql

DOCKER ###########################################
docker-compose
docker-mysql
docker-kafka
docker-kafkaUi
docker-influxdb

Docker up
```
docker compose -v up -d
```

Docker down
```
docker compose down
```

DB ####################################################
Mysql - docker
The inner port has to be 3306
example: 3307:3306


Services ###################################################
user-service
    contains alerting for energy consumption - utilises emails
docker mysql
flyway db migration tool - version control of migrations
    all changes to schemas, tables etc. requires resources/db.migration sql files
    it is executed during user-device app startup but handles all servcies!
device-service
    1 user can have multiple devices - 1 device can have only one user
global exception
aol for logging and time measurement
ingestion-service
    all devices will be sending data (electrical information, power consumption etc)
    ingestion service will handle the data (TB per day) and post it to db
kafka docker
kafka ui docker
usage-service
docker influx-db
ingestion - kafka
kafka - usage - influx db


Annotations to remember ##################################
@AfterReturning -> aop
@AllArgsConstructor -> lombok
@Aspect -> aop -> allows to create a functionality that reflects to a group of methods
    for example -> to all service methods -> check pointcut, before, afterReturning
@Async -> asynchronous -> check @EnableAsync
@Autowired -> to utilise the @component, repository
    not required if only one param is injected via constructor
@Bean -> part of the @Configuration
@Before -> aop
@Builder -> lombok for getters and setters
@Column -> name in brackets points to the schema column name
    @Column(unique = true)
@Component -> in a java class is to tell to spring: 
    Register me automatically at the startup in your context 
    so any other class could use me using dependency injection: @Autowire
@Configuration -> and @Bean are used to add manually a java class to the spring context 
    at the startup. Commonly @Bean is used when you can't annotate a class with some annotation. 
    Sample: Libraries not compatible with spring, old libraries or just any class that requires more than a simple instantiation.
@ControllerAdvice -> this mapping checks if a class that handles exceptions (global exception handler)
    knows how to handle controller thrown exceptions
@Data -> lombok for getters and setters builder pattern
@DeleteMapping
@Disabled -> to disable unit test, for example finished seed or just broken test
@Email -> validation
    @Email(message = "invalid email format")
@EnableAsync -> required by async call, place in start application class
@EnableScheduling -> required by scheduler, place in start application class
@Entity
@Enumerated -> in case of Enum usage by db entity - if we want to save actual enum instead of its index
    @Enumerated(EnumType.STRING)  
@ExceptionHandler -> utilised by global exception class
@GeneratedValue(strategy= GenerationType.IDENTITY)
@GetMapping
@Id -> entity id
@JoinColumn -> relations
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
)
@JoinTable -> relations
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id"))
@JsonFormat -> useful for type serialization
    @JsonFormat(shape = JsonFormat.Shape.STRING)
@JsonIgnoreProperties -> jackson mapping parameter
    @JsonIgnoreProperties(ignoreUnknown = true) -> self-explanatory
@JsonInclude -> jackson mapping parameter
    @JsonInclude(JsonInclude.Include.NON_NULL) -> include only non-null parameters
@JsonProperty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
@Lob -> large data type
@ManyToMany -> relationship
    @ManyToMany(fetch = FetchType.EAGER)
@ManyToOne -> relationship
@Max -> validation
@Max(10)
@Min -> validation
@Min(1)
@NoArgsConstructor -> lombok
@NotBlank - validator
    @NotBlank(message = "password is required")
@NotNull - validator
    @NotNull(message = "price is required")
@NullMarked -> return value not null
@OneToMany -> relationship
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
@OneToOne -> relationship
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
@Override -> inheritance for example new version of abstract function
@Param -> Query param:
    @Query("SELECT CASE WHEN COUNT(oi) > 0 THEN true ELSE false END FROM OrderItem oi " +
    "WHERE oi.order.id = :oderId AND oi.menu.id = :menuId")
    boolean existsByOrderIdAndMenuId(
    @Param("orderId") Long oderId, @Param("menuId") Long menuId); }
@PathVariable -> client simple parameter
@Pointcut -> aop -> information to apply -> for example to all service method
    @Pointcut("execution(* figura.user_service.service.*.*(..))")
@Positive -> validator
    @Positive(message = "price must be a positive value")
@PostConstruct -> execute included method right after object initialization
    @PostConstruct
    private void init() { System.out.println("Iniciando JWT utilizando..."); }
@PostMapping -> for mapper
@PreAuthorize -> java authorization
    @PreAuthorize("hasAuthority('ADMIN')")
@PreDestroy -> for example - shutdown of threads
@PutMapping
@Query -> jpa query
    @Query("SELECT COUNT(DISTINCT o.user.id) FROM Order o")
@Repository -> jpa
    public interface OrderRepository extends JpaRepository<Order, Long>
    List<Order> findByUserOrderByOrderDateDesc(User user);
@RequestBody -> if expecting model from the client
@RequestMapping -> custom address "/api/blablabla"
@RequiredArgsConstructor -> lombok
@ResponseStatus -> this automatically sends back the status
    we don't have to return it
@RestController
@Scheduled -> scheduler to run functionality, requires enabling
    go to @EnableScheduling
    - for example method @Scheduled(fixedRate = 5000) run every 5s
@Service
@Size -> validation
    @Size(min = 4, message = "password must contain at least 4 characters")
@Slf4j -> lombok logger "log.info()..."
@SpringBootApplication -> always in the startup class
@SpringBootTest
@Table -> schema table
@Test
@Value -> property from configuration
    @Value("${spring.mail.host}")
    private String host;

shortcuts#########################
ctrl shift t -> go to test
ctrl / -> comment uncomment
alt f12 -> terminal