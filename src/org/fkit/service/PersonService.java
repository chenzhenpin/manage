package org.fkit.service;

import org.fkit.entity.one.Person;

public interface PersonService {
  void savePerson();
  Person loadPerson(Long id);
  void  OneToOne();
  void  ManyToOne();
  void  OneToMany();
  void  ManyToMany();
}