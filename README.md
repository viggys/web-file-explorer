# MyPC
MyPC [My Personal Cloud] is a web file explorer to browse files and directories of the system where the application is running.

This is your personal cloud application which you can access within your local network using an Internet Browser.


### What you can do?

- Browse Files and Directories
- Create Files and Directories
- View File Data
- Update File Data
- Delete Files
- Delete Empty Directories
- Show/Hide Hidden Files

### Security
Configure your Login credentials for the application using the below environment variables.

>__MYPC_USERNAME__ _(plain text)_
>
>__MYPC_PASSWORD__ _(BCrypt Encrypted)_

For MacOS/Linux,
```shell script
export MYPC_USERNAME='...'
export MYPC_PASSWORD='...'
```

For Windows,
```shell script
set MYPC_USERNAME='...'
set MYPC_PASSWORD='...'
```

Generate your encrypted password from [here](https://bcrypt-generator.com).

---
