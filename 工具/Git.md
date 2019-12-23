# 分支操作

1. git branch 创建分支
2. git branch -b 创建并切换到新建的分支上
3. git checkout 切换分支
4. git branch 查看分支列表
5. git branch -v 查看所有分支的最后一次操作
6. git branch -vv 查看当前分支
7. git brabch -b 分支名 origin/分支名 创建远程分支到本地
8. git branch --merged 查看别的分支和当前分支合并过的分支
9. git branch --no-merged 查看未与当前分支合并的分支
10. git branch -d 分支名 删除本地分支
11. git branch -D 分支名 强行删除分支
12. git branch origin :分支名 删除远处仓库分支
13. git merge 分支名 合并分支到当前分支上

# 暂存操作

1. git stash 暂存当前修改
2. git stash apply 恢复最近的一次暂存
3. git stash pop 恢复暂存并删除暂存记录
4. git stash list 查看暂存列表
5. git stash drop 暂存名(例：stash@{0}) 移除某次暂存
6. git stash clear 清除暂存

# 回退操作

1. git reset --hard HEAD^ 回退到上一个版本
2. git reset --hard ahdhs1(commit_id) 回退到某个版本
3. git checkout -- file撤销修改的文件(如果文件加入到了暂存区，则回退到暂存区的，如果文件加入到了版本库，则还原至加入版本库之后的状态)
4. git reset HEAD file 撤回暂存区的文件修改到工作区

# 标签操作

1. git tag 标签名 添加标签(默认对当前版本)
2. git tag 标签名 commit_id 对某一提交记录打标签
3. git tag -a 标签名 -m '描述' 创建新标签并增加备注
4. git tag 列出所有标签列表
5. git show 标签名 查看标签信息
6. git tag -d 标签名 删除本地标签
7. git push origin 标签名 推送标签到远程仓库
8. git push origin --tags 推送所有标签到远程仓库
9. git push origin :refs/tags/标签名 从远程仓库中删除标签

# 常规操作

1. git push origin test 推送本地分支到远程仓库
2. git rm -r --cached 文件/文件夹名字 取消文件被版本控制
3. git reflog 获取执行过的命令
4. git log --graph 查看分支合并图
5. git merge --no-ff -m '合并描述' 分支名 不使用Fast forward方式合并，采用这种方式合并可以看到合并记录
6. git check-ignore -v 文件名 查看忽略规则
7. git add -f 文件名 强制将文件提交

## git创建项目仓库

将已有代码传到新仓库：

```shell
git init
git add .
git commit -m ""
git remote add origin ssh@
git push -u origin master
```

## git提交本地代码

```shell
git status
git add .
git commit -am ""
git push
```

## git更新代码到本地

```shell
# 本地没有仓库，直接全部拉取
git clone
# 本地代码落后，将更新的代码拉取到本地
git pull -u origin master:master
```

### 忽略已加入到版本库中的文件

1. git update-index --assume-unchanged file 忽略单个文件
2. git rm -r --cached 文件/文件夹名字 (. 忽略全部文件)

### 取消忽略文件

1. git update-index --no-assume-unchanged file

### 拉取、上传免密码

1. git config --global credential.helper store

### 更新远程仓库到本地

#### 查看远程分支

```shell
$ git remote -v
```

#### 从远程获取最新版本到本地：

在本地新建一个temp分支，并将远程origin仓库的master分支代码下载到本地temp分支

```shell
$ git fetch origin master:temp
```

#### 比较本地仓库与下载的temp分支

```shell
$ git diff temp
```

#### 合并temp分支到本地的master分支

```shell
$ git merge temp
```

#### 删除temp分支

```shell
$ git branch -d temp
```

## git项目添加小组成员

https://www.jianshu.com/p/0f23e290e874



## 分支提交

多人维护同一个项目，每个人应当有一个分支，在分支中进行push，最后合并代码；

### 1. 新建分支

```shell
git branch xxx（个人分支）
# 查看所有分支
git branch -a
```

### 2. 修改或添加代码之后

```shell
git add . 
git commit -m "comments"
```

### 3. 提交本地代码到分支

```shell
git push origin xxx (xxx为分支名称)
```

## 分支合并到主干

master端合并分支

```shell
# 查看所有分支
git branch -a
# checkout分支
git checkout master
# 合并——>这里可能报错，代码冲突CONFLICT
git merge xxx （分支）
# 完成之后push
git push origin master
```

