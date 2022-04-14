package edu.cnm.deepdive.tvnservice.controller;

public final class PathComponents {

  public static final String UUID_PATTERN = "\\p{XDigit}{8}-(?:\\p{XDigit}{4}-){3}\\p{XDigit}{12}";
  public static final String ORGANIZATION_ID_PATTERN = "{organizationId:" + UUID_PATTERN + "}";
  public static final String OPPORTUNITY_ID_PATTERN = "{opportunityId:" + UUID_PATTERN + "}";
  public static final String USER_ID_PATTERN = "{userId:" + UUID_PATTERN + "}";

  public static final String USERS = "/users";
  public static final String CURRENT_USER = "/me";
  public static final String ALL_FAVORITES = CURRENT_USER + "/favorites";
  public static final String FAVORITE_ORG = ALL_FAVORITES + "/" + ORGANIZATION_ID_PATTERN;
  public static final String ALL_VOLUNTEERS = CURRENT_USER + "/volunteers";
  public static final String VOLUNTEER_ORG = ALL_VOLUNTEERS + "/" + ORGANIZATION_ID_PATTERN;
  public static final String ALL_OWNED_ORGANIZATIONS = CURRENT_USER + "/organizations";

  public static final String ORGANIZATIONS = "/organizations";
  public static final String SPECIFIC_ORGANIZATION = "/" + ORGANIZATION_ID_PATTERN;
  public static final String ORGANIZATION_NAME = SPECIFIC_ORGANIZATION + "/name";

  public static final String OPPORTUNITIES = "/opportunities";
  public static final String ORGANIZATION_OPPORTUNITIES = ORGANIZATIONS + SPECIFIC_ORGANIZATION + OPPORTUNITIES;
  public static final String SPECIFIC_OPPORTUNITY = "/" + OPPORTUNITY_ID_PATTERN;

  public static final String OPPORTUNITIES_SEARCH = ORGANIZATIONS + "/all" + OPPORTUNITIES;

  private PathComponents() {
  }



}
