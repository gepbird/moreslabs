{
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs?ref=nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils"; # provides eachDefaultSystem
  };

  outputs = inputs:
    inputs.flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import inputs.nixpkgs { inherit system; };
      in
      with pkgs; {
        devShells.default = mkShell {
          packages = with pkgs; [
            jdk21
            jetbrains.idea-community
          ];
          shellHook = ''
            export LD_LIBRARY_PATH="''${LD_LIBRARY_PATH}''${LD_LIBRARY_PATH:+:}${libglvnd}/lib"
          '';
        };
      }
    );
}
