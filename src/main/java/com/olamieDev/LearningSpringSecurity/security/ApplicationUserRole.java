package com.olamieDev.LearningSpringSecurity.security;

import java.util.HashSet;
import java.util.Set;

import static com.olamieDev.LearningSpringSecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(new HashSet<>()),
    TEACHER(Set.of(COURSE_READ, COURSE_WRITE, STUDENT_WRITE, STUDENT_READ)),
    TEACHER_TRAINEE(Set.of(COURSE_READ, STUDENT_READ));

    private  final Set<ApplicationUserPermission> permissions;


    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
