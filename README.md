
# QASE.io

 is workspace for manual and automated tests.
Test management system for Dev and QA teams that helps to boost software testing efforts. I wrote tests (API and UI), which check "Defect" functionality of this site.


## Documentation

You can see a part of checklist in you follow link: [checklist](https://docs.google.com/spreadsheets/d/1hQ14n3alG6NmolFBpNmut7qDvlXK-5aM_eKDZUCKV-k/edit#gid=934965286)

## Summury
In my project I used different frameworks, tools, libraries and technologies like:

- **Maven**
- **TestNG**
- **Selenide**

The project has patterns such as **Page Object**, **Fluent/Chain of Invocations**, **Loadable Page**, **Value Object**.

If tests fail, thanks to TestNG tools(**RetryAnalyzer**, **TestListener** and **AnnotationTransformer**), they will be restarted.

Reporting is carried out by **Allure Report** framework

## Deployment

**Jenknis** is used to build and to run tests. 

The configuration of the pipeline is stored in `JenkinsFile`. The `JenkinsFile` is written using the Groovy DSL(declarative pipeline syntax).

## Environment Variables

Environment variables created on **Jenkins** for credentials and API token, which allows you to keep them safe and not show in the repository.

