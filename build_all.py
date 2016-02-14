import os, sys, shutil, fnmatch
import subprocess32 as subprocess

#-cp ../petablox.jar -Dpetablox.reflect.kind=dynamic -Dpetablox.run.analyses=cipa-0cfa-dlog petablox.project.Boot
def run_cmd(cmd, dir):
	p = subprocess.Popen(cmd,stdout=subprocess.PIPE,stderr=subprocess.PIPE, cwd=dir)
	try:
		out, err = p.communicate(timeout=1200)
	except subprocess.TimeoutExpired as err:
		print err


def build(dir, target):
	print ("Target {} on {}".format(target, dir))
	run_cmd(['ant', target], dir)


def main(target):
	current_dir = os.path.dirname(os.path.abspath(__file__))
	benchmark_dir = os.path.join(current_dir, "benchmarks")	
	
	for base_dir, between, file_name in os.walk(benchmark_dir):
		for cf in fnmatch.filter(file_name, 'build.xml'):
			build(os.path.abspath(base_dir), target)

if __name__ == '__main__':
	if len(sys.argv) < 2:
		print "run with build_all.py [build target]"
	else:
		main(sys.argv[1])