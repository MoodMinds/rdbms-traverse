# Synchronous Traverse implementation of the [RDBMS](https://github.com/MoodMinds/rdbms) SPI

This implementation of the [RDBMS](https://github.com/MoodMinds/rdbms) SPI supports synchronous execution traversal.
It is built on the [Routes Traverse](https://github.com/MoodMinds/routes-traverse) and the JDBC API. This can be utilized
as a client over JDBC, providing a more user-friendly streaming API for database management.

## Examples

Similar to the [Routes Traverse](https://github.com/MoodMinds/routes-traverse), the materialized execution
representation is `Traversable`:

```java
import org.moodminds.rdbms.RdbmsException;
import org.moodminds.rdbms.route.Stream;
import org.moodminds.rdbms.traverse.ConnectionSource;
import org.moodminds.rdbms.traverse.route.Routes;

import java.util.logging.Logger;

class Sample {

    static final Logger LOG = Logger.getLogger(Sample.class.getName());

    static final Stream<String, RdbmsException> EMPLOYEES = $ -> $
            .relate(($$, t) -> $$.query("SELECT * FROM Employee"))
                .handle(employee -> $
                    .effect(employee, e -> LOG.info(e.<String>get("id"))) // peek
                    .supply(employee, e -> e.get("firstName")));

    ConnectionSource connectionSource = null; // imagine it is instantiated somehow

    public void traverse() {

        new Routes(connectionSource).stream(EMPLOYEES) // materialize to Traversable<String, RdbmsException>
            .traverse(t -> t.each(s -> LOG.info(s)));
    }
}
```

## Maven configuration

Artifacts can be found on [Maven Central](https://search.maven.org/) after publication.

```xml
<dependency>
    <groupId>org.moodminds.rdbms</groupId>
    <artifactId>rdbms-traverse</artifactId>
    <version>${version}</version>
</dependency>
```

## Building from Source

You may need to build from source to use **RDBMS Traverse** (until it is in Maven Central) with Maven and JDK 9 at least.

## License
This project is going to be released under version 2.0 of the [Apache License][l].

[l]: https://www.apache.org/licenses/LICENSE-2.0