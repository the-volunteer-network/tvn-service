package edu.cnm.deepdive.tvnservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.tvnservice.model.dao.OrganizationRepository;
import edu.cnm.deepdive.tvnservice.model.dao.UserRepository;
import edu.cnm.deepdive.tvnservice.model.entity.Opportunity;
import edu.cnm.deepdive.tvnservice.model.entity.Organization;
import edu.cnm.deepdive.tvnservice.model.entity.User;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Profile("preload")
@Component
public class OrganizationPreloader implements CommandLineRunner {

  private final UserRepository userRepository;
  private final OrganizationRepository organizationRepository;
  private final ObjectMapper mapper;

  @Value("${user-id}")
  private UUID userId;

  @Autowired
  public OrganizationPreloader(UserRepository userRepository,
      OrganizationRepository organizationRepository,
      ObjectMapper mapper) {
    this.userRepository = userRepository;
    this.organizationRepository = organizationRepository;
    this.mapper = mapper;
  }

  @Override
  public void run(String... args) throws Exception {
    User owner = userRepository
        .findByExternalKey(userId)
        .orElseThrow();
    ClassPathResource resource = new ClassPathResource("preload/organizations.json");
    try (InputStream input = resource.getInputStream()) {

      Organization[] organizations = mapper.readValue(input, Organization[].class);
      List<Organization> orgList = Arrays
          .stream(organizations)
          .peek((org) -> {
            org.setOwner(owner);
            for (Opportunity opp: org.getOpportunities()) {
              opp.setOrganization(org);
            }
          })
          .collect(Collectors.toList());
      organizationRepository.saveAll(orgList);
    }
  }
}
