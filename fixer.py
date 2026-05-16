import re

with open('build.log', 'r') as f:
    lines = f.readlines()

errors = {}
for i, line in enumerate(lines):
    if 'error:' in line:
        parts = line.split('.java:', 1)
        if len(parts) == 2:
            file = parts[0] + '.java'
            line_num = int(parts[1].split(':', 1)[0])
            error_msg = parts[1].split('error:')[1].strip()
            if file not in errors:
                errors[file] = []
            errors[file].append((line_num, error_msg))

for file, errs in errors.items():
    if not 'D:\\' in file: continue
    
    with open(file, 'r', encoding='utf-8') as f:
        content = f.readlines()
        
    for line_num, err in errs:
        idx = line_num - 1
        line = content[idx]
        if 'SlimefunItemStack cannot be converted to ItemStack' in err:
            # Add .item() before the last closing paren or comma
            pass
            
    with open(file, 'w', encoding='utf-8') as f:
        f.writelines(content)

print("Parsed", len(errors), "files with errors")
