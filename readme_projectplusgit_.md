## Project type:
1. JavaEE
2. Glassfish
3. Payara 5
4. Web-app
5. Maven

## Create project:
1. Java EE
2. Glassfish
3. Java 11
4. All select modules and features

## Edit Configure (For running application on Glassfish)
1. Update name (optional)
2. Create war exploded/war
3. Use the war exploded artifact
4. Apply + OK

## GITLab (For creating the project the first time only!!!)
1. Create new project
2. Use these command (already given in gitlab)
   ```
   cd <existing_repo>
   git init
   git remote add origin https://gitlab.uni-koblenz.de/javaee-2022-teamnine/javaee-2022-teamnine.git
   git branch -M master
   git commit -m "initial commit"
   git push -uf origin master (but first, remove branch as protected)
   ```
# Git Essentials
## To set email and username for gitlab (do this step before the first push)
```
   git config --global user.email "username@uni-koblenz.de"
   git config --global user.name "username"
```
## Configure entering username and password only once
### For Intellij: 
Go to Git -> Manage Remotes... -> select the branch/repo. -> enter username/password -> OK

## Create new branch and push changes (all from console) (this clones from master branch)
   ```
   git branch <branch_name> (create a branch in local)
   git checkout <branch_name> (change to a branch)
   git branch (check your current branch)
   git commit -m "msg"
   git push --set-upstream origin dev-1
       > do this only for the initial push, other wise, just use git push
       > pushes to remote
   ```
## Create new branch from existing branch
   ```   
   git checkout -b <new-branch> <old-branch>
   ```
>Example:
>   ```
>> git checkout -b dev-2 dev-1
>  ```
>  Here, dev-2 branch is being created from dev-1 branch and also checked out at the same time.

## Basic flow for pushing changes
  ```   
   git add .
  ```   
   The '.' adds all the files that have changes to the staging phase.
   You can add files individually as well, e.g., git add /path/to/the/file/...
   ```
   git commit -m "<commit message>"
   ```
   -m is like a descriptor for the commit message
   ```
   git push
   ```

## Basic flow to pull a repo.
   ```
   git fetch
   ```
   Tells your local git to retrieve the latest meta-data info from the original.
   The fetch command downloads the changes and files into the local repo. 
   You can use git fetch to know the changes done in the remote repo/branch since your last pull. 
   This is useful to allow for checking before doing an actual pull, which could change files in your current branch and working copy (and potentially lose your changes, etc).
   ```
   git pull
   ```
   Pull does what fetch does and brings a copy of those changes from the remote repository.
   You can checkout to your desired branch first then fetch and pull the changes OR, fetch and pull then move to the desired branch.

## Merging branches
   Try to have as less conflicts as possible while merging to avoid merge conflicts.

   > Checkout to the branch you want to merge.
   > Example: You want to merge branch dev-1 to master
   
``` git merge dev-1```

## Cloning from gitlab
Can be done via 2 ways
1. HTTPS (Clone with HTTPS when you want to authenticate each time you perform an operation between your computer and GitLab)
   ```
   git clone https://gitlab.uni-koblenz.de/javaee-2022-teamnine/javaee-2022-teamnine.git
   ```
2. SSH (Clone with SSH when you want to authenticate only one time)
   ```
   git clone git@gitlab.uni-koblenz.de:javaee-2022-teamnine/javaee-2022-teamnine.git
   ```

> Cloning can be done through the IDE's functionalities too!

## Git Cheat Sheet: [Link](https://about.gitlab.com/images/press/git-cheat-sheet.pdf?_gl=1*1ise22r*_ga*MTY2NzA1NjQwNy4xNjYyNDAzOTg3*_ga_ENFH3X7M5Y*MTY2MjQxMjM2My4zLjAuMTY2MjQxMjM2My4wLjAuMA..)


