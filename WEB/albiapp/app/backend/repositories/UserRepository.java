package backend.repositories;
import backend.models.FlatUser;
import play.db.jpa.JPAApi;

import javax.inject.Inject;


public class UserRepository extends GenericRepository<FlatUser> {
    @Inject
    public UserRepository(JPAApi jpaApi) {
        super(FlatUser.class, jpaApi);
    }
}
