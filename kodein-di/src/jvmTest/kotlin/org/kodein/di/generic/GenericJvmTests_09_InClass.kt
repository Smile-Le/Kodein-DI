package org.kodein.di.generic

import org.kodein.di.DI
import org.kodein.di.erased.DEPRECATED_ERASED_GENERIC_7X
import org.kodein.di.test.FixMethodOrder
import org.kodein.di.test.MethodSorters
import org.kodein.di.test.Person
import kotlin.test.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Deprecated(DEPRECATED_ERASED_GENERIC_7X)
class GenericJvmTests_09_InClass {

    class PersonContainer(di: DI) {
        val newPerson: () -> Person by di.provider()
        val salomon: Person by di.instance(tag = "named")
        val factory: (String) -> Person by di.factory(tag = "factory")
    }

    @Test
    fun test_00_Class() {
        val kodein = DI {
            bind<Person>() with provider { Person() }
            bind<Person>(tag = "named") with singleton { Person("Salomon") }
            bind<Person>(tag = "factory") with factory { name: String -> Person(name) }
        }

        val container = PersonContainer(kodein)
        assertNotSame(container.newPerson(), container.newPerson())
        assertEquals("Salomon", container.salomon.name)
        assertSame(container.salomon, container.salomon)
        assertNotSame(container.factory("Laila"), container.factory("Laila"))
        assertEquals("Laila", container.factory("Laila").name)
    }

}
